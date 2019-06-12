package part2.observer;

/**
 * 八进制
 *
 * @author Nathan
 */
public class OctalObserver extends AbstractObserver {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String:" + Integer.toOctalString(subject.getState()));
    }
}
