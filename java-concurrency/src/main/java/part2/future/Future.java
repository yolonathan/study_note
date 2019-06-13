package part2.future;

/**
 * @author Nathan
 */
public interface Future<T> {

    T get() throws InterruptedException;

}
