package part2.observer.thread;

import java.util.Arrays;

/**
 * @author Nathan
 */
public class ThreadLifeCycleClient {

    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2"));
    }
}
