package part2.observer;

/**
 * @author Nathan
 */
public abstract class AbstractObserver {

    protected Subject subject;

    public AbstractObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    protected abstract void update();
}
