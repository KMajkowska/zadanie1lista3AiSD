import java.util.Iterator;

public interface IList<E> extends Iterable <E>
{
	
	// FUNKCJONALNOŒÆ PODSTAWOWA
	 boolean add(E e); 
	 // dodanie elementu na koniec listy
	 void add(int index, E element); 
	 // dodanie elementu na podanej pozycji
	 void clear(); 
	 // skasowanie wszystkich elementów
	 boolean contains(E element); 
	 // czy lista zawiera podany element (equals())
	 E get(int index); 
	 // pobranie elementu z podanej pozycji
	 E set(int index, E element); 
	 // ustawienie nowej wartoœci na pozycji
	 int indexOf(E element); 
	 // pozycja szukanego elementu (equals())
	 boolean isEmpty(); 
	 // czy lista jest pusta
	 Iterator<E> iterator(); 
	 Iterator<E> revIterator();
	 // zwraca iterator przed pierwsz¹ pozycj¹
	 E remove(int index); 
	 // usuwa element z podanej pozycji
	 boolean remove(E e); 
	 // usuwa element (equals())
	 int size(); // rozmiar listy
	 
	 // ===================================================
	 // FUNKCJONALNOŒÆ ROZSZERZONA
	 // ===================================================
	 void reverseInPlace();
	 // odwraca kolejnoœæ elementów w liœcie
	 
	 IList<E> reverseCreate();
	 // tworzy obiekt nowej listy o odwróconej kolejnoœci elementów, 
	 // oryginalna lista nie jest zmieniana 
	 void decimate( boolean del_even );
	 // usuwa co drugi element z listy, jeœli del_even == true usuwany 
	 // element o indeksach 0,2,4 .. w p.p. 1,3,5,…
	 int moveToBegin(E e );
	 // przesuwa na pocz¹tek listy wszystkie elementy równe e; zwraca 
	 // liczbê przesuniêtych elementów
	 IList<E> merge( IList<E> other, boolean append );
	 // tworzy nowy obiekt listy bêd¹cy po³¹czeniem samej siebie i listy 
	 // other, jeœli append==true to other do³¹czany jest na koñcu, 
	 // w p.p. na pocz¹tku
	 IList<E> sublist( int start_ind, int end_ind );
	 // tworzy now¹ listê zawieraj¹c¹ element o indeksach od start_ind do 
	 // end_ind, jeœli start_ind jest powy¿ej size to zwracana jest lista 
	 // pusta, jeœli end_ind jest powy¿ej size to zwracane s¹ elementy od 
	 // start_ind do koñca listy
	 
	 
}