import java.util.Iterator;

public interface IList<E> extends Iterable <E>
{
	
	// FUNKCJONALNO�� PODSTAWOWA
	 boolean add(E e); 
	 // dodanie elementu na koniec listy
	 void add(int index, E element); 
	 // dodanie elementu na podanej pozycji
	 void clear(); 
	 // skasowanie wszystkich element�w
	 boolean contains(E element); 
	 // czy lista zawiera podany element (equals())
	 E get(int index); 
	 // pobranie elementu z podanej pozycji
	 E set(int index, E element); 
	 // ustawienie nowej warto�ci na pozycji
	 int indexOf(E element); 
	 // pozycja szukanego elementu (equals())
	 boolean isEmpty(); 
	 // czy lista jest pusta
	 Iterator<E> iterator(); 
	 Iterator<E> revIterator();
	 // zwraca iterator przed pierwsz� pozycj�
	 E remove(int index); 
	 // usuwa element z podanej pozycji
	 boolean remove(E e); 
	 // usuwa element (equals())
	 int size(); // rozmiar listy
	 
	 // ===================================================
	 // FUNKCJONALNO�� ROZSZERZONA
	 // ===================================================
	 void reverseInPlace();
	 // odwraca kolejno�� element�w w li�cie
	 
	 IList<E> reverseCreate();
	 // tworzy obiekt nowej listy o odwr�conej kolejno�ci element�w, 
	 // oryginalna lista nie jest zmieniana 
	 void decimate( boolean del_even );
	 // usuwa co drugi element z listy, je�li del_even == true usuwany 
	 // element o indeksach 0,2,4 .. w p.p. 1,3,5,�
	 int moveToBegin(E e );
	 // przesuwa na pocz�tek listy wszystkie elementy r�wne e; zwraca 
	 // liczb� przesuni�tych element�w
	 IList<E> merge( IList<E> other, boolean append );
	 // tworzy nowy obiekt listy b�d�cy po��czeniem samej siebie i listy 
	 // other, je�li append==true to other do��czany jest na ko�cu, 
	 // w p.p. na pocz�tku
	 IList<E> sublist( int start_ind, int end_ind );
	 // tworzy now� list� zawieraj�c� element o indeksach od start_ind do 
	 // end_ind, je�li start_ind jest powy�ej size to zwracana jest lista 
	 // pusta, je�li end_ind jest powy�ej size to zwracane s� elementy od 
	 // start_ind do ko�ca listy
	 
	 
}