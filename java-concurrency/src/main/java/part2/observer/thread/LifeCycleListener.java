package part2.observer.thread;

/**
 * @author Nathan
 */
public interface LifeCycleListener {

    void onEvent(AbstractObservableRunnable.RunnableEvent event);
}
