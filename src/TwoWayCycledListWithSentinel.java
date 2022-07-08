import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayCycledListWithSentinel<E> extends AbstractList<E>{

	private class Element{
		
		private E value;
		private Element next;
		private Element prev;
		
		public E getValue() {
			return value; 
		}
		
		public void setValue(E value) {
			this.value = value; 
		}
		
		public Element getNext() {
			return next;
		}
		
		public void setNext(Element next) {
			this.next = next;
		}
		
		public Element getPrev() {
			return prev;
		}
		
		public void setPrev(Element prev) {
			this.prev = prev;
		}
		
		Element(E data){
			this.value=data;
		}
		/** elem będzie stawiony <b> za this </b>*/
		
		public void insertAfter(Element elem){
			elem.setNext(this.getNext());
			elem.setPrev(this);
			this.getNext().setPrev(elem);
			this.setNext(elem);
		}
			/** elem będzie stawiany <b> przed this </b>*/
		
		public void insertBefore(Element elem){
			elem.setNext(this);
			elem.setPrev(this.getPrev());
			this.getPrev().setNext(elem);
			this.setPrev(elem);
		}
		/** elem będzie usuwany z listy w której jest <p>
		* <b>Założenie:</b> element jest już umieszczony w liście i nie jest to sentinel */
		
		public void remove(){
			this.getNext().setPrev(this.getPrev());
			this.getPrev().setNext(this.getNext());
			}
		}
	
	Element sentinel = null;
	ArrayList<Iterator<E>>iterators;
	
	public TwoWayCycledListWithSentinel() {
		sentinel=new Element(null);
		sentinel.setNext(sentinel);
		sentinel.setPrev(sentinel);
		iterators = new ArrayList<Iterator<E>>();
	}
	
	private Element getElement(int index){
		if(index<0) throw new IndexOutOfBoundsException();
		Element elem=sentinel.getNext();
		int counter=0;
		while(elem!=sentinel && counter<index){
			counter++;
			elem=elem.getNext();
		}
		if(elem==sentinel) throw new IndexOutOfBoundsException();
		return elem;
		}
	
	private Element getSentinel() {
		return sentinel;
	}
	
	private Element getElement(E value){
		Element elem=sentinel.getNext();
		while(elem!=sentinel && !value.equals(elem.getValue())){
			elem=elem.getNext();
		}
		if(elem==sentinel)	return null;
		return elem;
		}
	
	public boolean isEmpty() {
		return sentinel.getNext()==sentinel;
	}
		
	public void clear() {
		sentinel.setNext(sentinel);
		sentinel.setPrev(sentinel);
	}
		
	public boolean contains(E value) {
		return indexOf(value)!=-1;
	}
		
	public E get(int index) {
		Element elem=getElement(index);
		return elem.getValue();
	}
	
	public E set(int index, E value) {
		Element elem=getElement(index);
		E retValue=elem.getValue();
		elem.setValue(value);
		return retValue;
	}
		
	public boolean add(E value) {
		Element newElem=new Element(value);
		sentinel.insertBefore(newElem);
		changed();
		return true;
	}
		
	public void add(int index, E value) {
		Element newElem=new Element(value);
		if(index==0) sentinel.insertAfter(newElem);
		else{
			Element elem=getElement(index-1);
			elem.insertAfter(newElem);
		}
		changed();
		}
	
	public int indexOf(E value) {
		Element elem=sentinel.getNext();
		int counter=0;
		while(elem!=sentinel && !elem.getValue().equals(value)) {
			counter++;
			elem=elem.getNext();
		}
		if(elem==sentinel) return -1;
		return counter;
		}
		
	public E remove(int index) {
		Element elem=getElement(index);
		elem.remove();
		changed();
		return elem.getValue();
	}
			
	public boolean remove(E value) {
		Element elem=getElement(value);
		if(elem==null) return false;
		elem.remove();
		changed();
		return true;
	}
			
	public int size() {
		Element elem=sentinel.getNext();
		int counter=0;
		while(elem!=sentinel){
			counter++;
			elem = elem.getNext();
		}
			return counter;
	}
			
	public Iterator<E> iterator() {
		Iterator<E> it = new TWCListIterator();
		iterators.add(it);
		return it;
	}

	private class TWCListIterator implements Changing<E>{
		boolean wasNext=false;
		boolean wasPrevious=false;
		Element _current=sentinel;
		boolean list_change = false;
		
		public boolean hasNext() throws ConcurrentModificationException{
			if(list_change == true)
            	throw new ConcurrentModificationException();
			return _current.getNext()!=sentinel;
		}
		
		public boolean hasPrevious() throws ConcurrentModificationException{
			if(list_change == true)
            	throw new ConcurrentModificationException();
			return _current!=sentinel;
		}
		
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		
		public E next() throws ConcurrentModificationException{
			if(list_change == true)
            	throw new ConcurrentModificationException();
			wasNext=true;
			wasPrevious=false;
			_current=_current.getNext();
			return _current.getValue();
		}
			
		public E previous() throws ConcurrentModificationException{
			if(list_change == true)
            	throw new ConcurrentModificationException();
			wasNext=false;
			wasPrevious=true;
			E retValue=_current.getValue();
			_current=_current.getPrev();
			return retValue;
		}
		
		public void remove() throws ConcurrentModificationException{
			if(list_change == true)
            	throw new ConcurrentModificationException();
			if(wasNext){
				Element curr=_current.getPrev();
				_current.remove();
				_current=curr;
				wasNext=false;
			}
			if(wasPrevious){
				_current.getNext().remove();
				wasPrevious=false;
			}
		}
			
		public void add(E data) throws ConcurrentModificationException{
			if(list_change == true)
            	throw new ConcurrentModificationException();
			Element newElem=new Element(data);
			_current.insertAfter(newElem);
			_current=_current.getNext();
		}
		
			public void set(E data) throws ConcurrentModificationException{
				if(list_change == true)
	            	throw new ConcurrentModificationException();
				if(wasNext){
					_current.setValue(data);
					wasNext=false;
				}
				if(wasPrevious){
					_current.getNext().setValue(data);
					wasNext=false;
					}
				}
			
			public void wasChange() {
				list_change = true;
			}
	}

	@Override
	public void reverseInPlace() {
		// odwraca kolejność elementów w liście
		Element previous = sentinel.getPrev();
		Element current = sentinel;
		Element next;
		do {
			next = current.getNext(); //next ustawia na nast�pny element
			current.setNext(previous);
			current.setPrev(next);// nast�pny po curent ustawia na poprzedni
			previous = current; // poprzedni ustawia na aktualny
			current = next; // aktualny ustawia na poprzedni
		}while (current!= sentinel);
		changed();
	}

	@Override
	public IList<E> reverseCreate() {
		// tworzy obiekt nowej listy o odwróconej kolejności elementów,
		TwoWayCycledListWithSentinel<E> list_reverse = new TwoWayCycledListWithSentinel<E>();
		Element previous = sentinel;
		Element next = previous.getPrev();
		while(next != sentinel) {
			list_reverse.add(next.getValue());
			previous = next;
			next = previous.getPrev();
		}
		
		return  list_reverse;
	}

	@Override
	public void decimate(boolean del_even) {
		// usuwa co drugi element z listy, jeśli del_even == true usuwany
		// element o indeksach 0,2,4 .. w p.p. 1,3,5,...
		Element actElem = sentinel.getNext();
		int index=0, to_remove;
		if(del_even)
			to_remove=0;
		else
			to_remove=1;
		while(actElem.getNext() != sentinel)
		{
			if(index%2==to_remove)
			{
				actElem.getNext().setPrev(actElem.getPrev());
				actElem.getPrev().setNext(actElem.getNext());
			}
			actElem = actElem.getNext();
			index++;
		}
		changed();
	}

	public int moveToBegin(E e ) 
	{
		// przesuwa na początek listy wszystkie elementy równe e; zwraca
		// liczbę przesuniętych elementów
		
		int number = 0;
		Element first = sentinel.getNext();
		Element next = first;
		Element set = new Element(e);
		while(first != sentinel)
		{
			next = first.getNext();
			if(first.getValue().equals(e))
			{
				number++;
				first.getNext().setPrev(first.getPrev()); //ta i nast�pna linijka to w sumie takie remove tylko zrobione r�cznie, wi�c optymalniejsze
				first.getPrev().setNext(first.getNext()); //z�o�ono�v tego powinna by� sta�a
				add(0,e); //a tutaj na pozycj� 0 dodaje elemet, a z�o�ono�c tego to O(1)
			}
			first = next;
		}
		changed();
		return number;
	}

	@Override
	public IList<E> merge(IList<E> other, boolean append) {
		// tworzy nowy obiekt listy będący połączeniem samej siebie i listy
		// other, jeśli append==true to other dołączany jest na końcu,
		// w p.p. na początku
		TwoWayCycledListWithSentinel<E> List = new TwoWayCycledListWithSentinel<E>();
		Element actElem = sentinel.getNext();
		
		if(append) {
			while(actElem != sentinel) {
				List.add(actElem.getValue());
				actElem = actElem.getNext();
			}
			for(E e : other) {
				List.add(e);
			}
		}
		else {
			for(E e : other) {
				List.add(e);
			}
			while(actElem != sentinel) {
				List.add(actElem.getValue());
				actElem = actElem.getNext();
			}
		}
		return List;
	}

	@Override
	public IList<E> sublist(int start_ind, int end_ind) {
		// tworzy nową listę zawierającą element o indeksach od start_ind do
		// end_ind, jeśli start_ind jest powyżej size to zwracana jest lista
		// pusta, jeśli end_ind jest powyżej size to zwracane są elementy od
		// start_ind do końca listy.
		TwoWayCycledListWithSentinel<E> list = new TwoWayCycledListWithSentinel<E>();
		if(start_ind >= size()) 
		{
			list = null;
			return list;
		}
		int i = 0;
		Element actElem = sentinel.getNext();
		while(i<start_ind && actElem.getNext() != sentinel)
		{
			i++;
			actElem = actElem.getNext();
		}
		while(i<=end_ind)
		{
			list.add(actElem.getValue());
			actElem = actElem.getNext();
			i++;
		}
		return list;
	}

	@Override
	public Iterator<E> revIterator() {
		// TODO Auto-generated method stub
		Iterator<E> it = new RevIterator<E>();
		iterators.add(it);
		return it;
	}	
	
	public void changed() {
		for(int i=0; i<iterators.size(); i++)
		{
			((Changing)iterators.get(i)).wasChange();
		}
	}
	
	private class RevIterator<E> implements Changing<E>{
        Element current;
        boolean list_change;

        public RevIterator(){
            current = sentinel;
            list_change = false;
        }

        public boolean hasNext() throws ConcurrentModificationException
        {
        	if(list_change == true)
            	throw new ConcurrentModificationException();
        	return current.getPrev() != sentinel;
        }

        public void wasChange()
        {
            list_change = true;
        }

        public E next() throws ConcurrentModificationException
        {
        	if(list_change == true)
            	throw new ConcurrentModificationException();
        	current = current.getPrev();
            return (E) current.getValue();
        }

        public void remove() throws ConcurrentModificationException{
        	if(list_change == true)
            	throw new ConcurrentModificationException();
        	if(current.getNext() != sentinel)
                current.getNext().remove();
            else throw new UnsupportedOperationException();
        }
    }
}