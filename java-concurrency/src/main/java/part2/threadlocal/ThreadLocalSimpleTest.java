package part2.threadlocal;

/**
 * @author Nathan
 */
public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Nathan";
        }
    };

    //JVM start main thread
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        String value = threadLocal.get();
        System.out.println(value);
    }
}
