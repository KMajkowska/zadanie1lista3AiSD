import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoWayCycledListWithSentinel<Integer> list = new TwoWayCycledListWithSentinel<Integer>();
		Main main = new Main();
		main.actions(list);
	}
	
	public void actions(TwoWayCycledListWithSentinel list)
	{
		int n=0;
		Scanner scan = new Scanner(System.in);
		do
		{
			showMenu();
			n = scan.nextInt();
			switch(n)
			{
			case 1:
			{
				char odp='t', w;
				int number=0;
				System.out.println("Wypelnic liste wprowadzonymi danymi czy wygenerowanymi przez program? [s/p]");
				w = scan.next().charAt(0);
				System.out.println("Ile wprowadzic wartosci?");
				int ile= scan.nextInt();
				for(int i=0; i<ile; i++ )
				{
					if(w == 's')
					{
						System.out.println("Podaj wartosc inta");
						number = scan.nextInt();
						if(!list.add(number))
						{
							System.out.println("Error");
						}
					}
					else
					{
						if(!list.add(number++))
						{
							System.out.println("Error");
						}
					}
				}
				break;
			}
			case 2:
			{
				System.out.println("Ile elementow chcesz dodac na koniec listy?");
				int ile, number=0;
				char w;
				ile = scan.nextInt();
				for(int i=0; i<ile; i++)
				{
					System.out.println("Dodac wlasne wartosci czy wygenerowane przez program?");
					w = scan.next().charAt(0);
					if(w == 's')
					{
						System.out.println("Podaj wartosc inta");
						number = scan.nextInt();
						list.add(number);
					}
					else
					{
						list.add(number++);
					}
				}
				break;
			}
			case 3:
			{
				System.out.println("Ile elementow chcesz dodac?");
				int ile, number=0, index;
				char w;
				ile = scan.nextInt();
				for(int i=0; i<ile; i++)
				{
					System.out.println("Dodac wlasne wartosci czy wygenerowane przez program?");
					w = scan.next().charAt(0);
					if(w == 's')
					{
						System.out.println("Podaj wartosc inta");
						number = scan.nextInt();
						System.out.println("Podaj indeks, na ktorym ma byc wstawiony element");
						index = scan.nextInt();
						list.add(index, number);
					}
					else
					{
						System.out.println("Podaj indeks, na ktorym ma byc wstawiony element");
						index = scan.nextInt();
						list.add(index, number++);
					}
				}
				break;
			}
			case 4:
			{
				list.clear();
				break;
			}
			case 5:
			{
				System.out.println("Ile elementow chcesz wyswietlic?");
				int ile, index;
				ile = scan.nextInt();
				for(int i=0; i<ile; i++)
				{
					System.out.println("Podaj indeks do wyswietlenia");
					{
						index = scan.nextInt();
						System.out.println(list.get(index));
					}
				}
				break;
			}
			case 6:
			{
				System.out.println("Ile elementow chcesz zmienic?");
				int ile, number=0, index;
				char w;
				ile = scan.nextInt();
				for(int i=0; i<ile; i++)
				{
					System.out.println("Dodac wlasne wartosci czy wygenerowane przez program?");
					w = scan.next().charAt(0);
					if(w == 's')
					{
						System.out.println("Podaj wartosc inta");
						number = scan.nextInt();
						System.out.println("Podaj indeks, na ktorym ma byc zmieniony element");
						index = scan.nextInt();
						list.set(index, number);
					}
					else
					{
						System.out.println("Podaj indeks, na ktorym ma byc wstawiony element");
						index = scan.nextInt();
						list.set(index, number);
						}
				}
				break;
			}
			case 7:
			{
				System.out.println("Ile elementow chcesz znalezc?");
				int ile, number=0, index;
				ile = scan.nextInt();
				for(int i=0; i<ile; i++)
				{
						System.out.println("Podaj wartosc inta");
						number = scan.nextInt();
						list.contains(number);
				}
				break;
			}
			case 8:
			{
				System.out.println(list.isEmpty());
				break;
			}
			case 9:
			{
				System.out.println("Ile elementow chcesz usun¹æ (po indeksach)?");
				int ile, number=0, index;
				ile = scan.nextInt();
				for(int i=0; i<ile; i++)
				{
						System.out.println("Podaj indeks");
						index = scan.nextInt();
						list.remove(index);
				}
				break;
			}
			case 10:
			{
				System.out.println("Ile elementow chcesz usun¹æ (wartosciach)?");
				int ile, number=0, index;
				ile = scan.nextInt();
				for(int i=0; i<ile; i++)
				{
						System.out.println("Podaj element");
						number = scan.nextInt();
						list.remove(number);
				}
				break;
			}
			case 11:
			{
				System.out.println(list.size());
				break;
			}
			case 12:
			{
				list.reverseInPlace();
				break;
			}
			case 13:
			{
				IList new_list = list.reverseCreate();
				Iterator it = new_list.iterator();
				while(it.hasNext())
				{
					System.out.println(it.next());
				}
				break;
			}
			case 14:
			{
				boolean remove;
				System.out.println("Chcesz usuawac elementy z indeksami parzystymi?");
				remove = scan.nextBoolean();
				list.decimate(remove);
				Iterator it = list.iterator();
				while(it.hasNext())
				{
					System.out.println(it.next());
				}
				break;
			}
			case 15:
			{
				System.out.println("Podaj wartosc ktora ma byc przeniesiona na poczatek");
				int tmp = scan.nextInt();
				System.out.println(list.moveToBegin(tmp));
				break;
			}
			case 16:
			{
				System.out.println("Pod³¹czyæ liste na pocz¹tek czy na koniec (true/false)?");
				boolean append = scan.nextBoolean();
				IList<Integer> other = list.reverseCreate();
				IList<Integer> newList = list.merge(other, append);
				Iterator it = newList.iterator();
				while(it.hasNext())
				{
					System.out.println(it.next());
				}
				break;
			}
			case 17:
			{
				int start, end;
				System.out.println("Podaj indeksy (start i koniec)");
				start = scan.nextInt();
				end = scan.nextInt();
				IList<Integer> sublist = new TwoWayCycledListWithSentinel<Integer>();
				sublist = list.sublist(start, end);
				Iterator it = sublist.iterator();
				while(it.hasNext())
				{
					System.out.println(it.next());
				}
				break;
			}
			case 18:
			{
				Iterator it = list.iterator();
				try {
					while(it.hasNext())
					{
						System.out.println(it.next());
					}
					}catch(ConcurrentModificationException e)
					{
						System.out.println(e.getMessage());
					}
				break;
			}
			case 19:
			{
				Iterator it = list.revIterator();
				try {
				while(it.hasNext())
				{
					System.out.println(it.next());
				}
				}catch(ConcurrentModificationException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			}
			case 20:
			{
				Iterator it = list.iterator();
				it.next();
				it.next();
				it.remove();
				}
			}
		}while(n!=0);
	}
	
	public void showMenu()
	{
		System.out.println("Menu:");
		System.out.println("1. Wypelnij liste");
		System.out.println("2. Dodaj element na koniec listy");
		System.out.println("3. Dodaj element na konkretn¹ pozycjê");
		System.out.println("4. Wyczyœæ listê");
		System.out.println("5. Wyœwietl konkretny element z listy");
		System.out.println("6. Zmieñ wartoœæ na konkretnej pozycji");
		System.out.println("7. Znajdz pozycje konkretnego elementu");
		System.out.println("8. Sprawdz czy lista jest pusta");
		System.out.println("9. Usun element z konkretnej pozycji");
		System.out.println("10. Usuwa konkretny element (po wartoœci tego elementu)");
		System.out.println("11. Sprawdz dlugosc listy");
		System.out.println("12. Odwroc elementy w liscie");
		System.out.println("13. Stworz nowa liste z odwroconymi elementami listy pierwszej");
		System.out.println("14. Usun co drugi element z tablicy");
		System.out.println("15. Przesun na poczatek wszystkie elementy, ktore maja podana wartosc");
		System.out.println("16. Polacz listy");
		System.out.println("17. Stworz nowa liste z wybranego fragmentu 'starej' listy");
		System.out.println("18. Wyswietl liste");
		System.out.println("19. Wyswietl liste od konca");
		System.out.println("20. Usun element z listy za pomoc¹ iteratora");
	}
	
}
