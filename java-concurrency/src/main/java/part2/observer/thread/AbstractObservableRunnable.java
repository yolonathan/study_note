package part2.observer.thread;

import lombok.Data;
import lombok.Getter;

/**
 * @author Nathan
 */
public abstract class AbstractObservableRunnable implements Runnable {

    final protected LifeCycleListener listener;

    public AbstractObservableRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    protected void notifyChange(final RunnableEvent event) {
        listener.onEvent(event);
    }


    @Getter
    public enum RunnableState {
        /**
         * 运行
         */
        RUNNING,
        /**
         * 错误
         */
        ERROR,
        /**
         * 完成
         */
        DONE
    }

    @Data
    public static class RunnableEvent {
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }
    }


}
