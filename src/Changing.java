import java.util.Iterator;
public interface Changing<E> extends Iterator<E> {
    public void wasChange();
}