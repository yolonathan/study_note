package part2.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nathan
 */
public class Subject {

    private List<AbstractObserver> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (state == this.state) {
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    public void attach(AbstractObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObserver() {
        observers.stream().forEach(AbstractObserver::update);
    }
}
