
public class ConcurrentModificationException extends ArrayIndexOutOfBoundsException {
	
	public String getMessage() 
	{
		return "Something was changed, iterator is not active anymore";
	}

}
