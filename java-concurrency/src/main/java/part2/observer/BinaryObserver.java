package part2.observer;

/**
 * 二进制
 *
 * @author Nathan
 */
public class BinaryObserver extends AbstractObserver {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    protected void update() {
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));
    }
}
