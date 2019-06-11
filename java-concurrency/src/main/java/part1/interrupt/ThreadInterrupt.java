package part1.interrupt;

/**
 * @author Nathan
 */
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {

            }
        });
        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        // 中断线程
        t.interrupt();
        System.out.println(t.isInterrupted());
    }

}
