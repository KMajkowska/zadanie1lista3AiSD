
public class Element<E> {
	
	private E value;
	private Element next;
	private Element prev;
	public E getValue() 
	{ 
		return value; 
	}
	public void setValue(E value) 
	{ 
		this.value = value; 
	}
	public Element getNext() 
	{
		return next;
	}
	public void setNext(Element next) 
	{
		this.next = next;
	}
	public Element getPrev() 
	{
		return prev;
	}
	public void setPrev(Element prev) 
	{
		this.prev = prev;
	}
	Element(E data)
	{
		this.value=data;
	}
	/** elem bêdzie stawiony <b> za this </b>*/
	public void insertAfter(Element elem)
	{
		elem.setNext(this.getNext());
		elem.setPrev(this);
		this.getNext().setPrev(elem);
		this.setNext(elem);
	}
	/** elem bêdzie stawiany <b> przed this </b>*/
	public void insertBefore(Element elem)
	{
		elem.setNext(this);
		elem.setPrev(this.getPrev());
		this.getPrev().setNext(elem);
		this.setPrev(elem);
	}
	/** elem bêdzie usuwany z listy w której jest <p>
	* <b>Za³o¿enie:</b> element jest ju¿ umieszczony w liœcie i nie jest to sentinel */
	public void remove()
	{
		this.getNext().setPrev(this.getPrev());
		this.getPrev().setNext(this.getNext());
	}
}
