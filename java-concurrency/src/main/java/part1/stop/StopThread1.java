package part1.stop;

/**
 * 利用中断 停止线程
 *
 * @author Nathan
 */
public class StopThread1 {

    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.start();

        Thread.sleep(10000);

        worker.interrupt();
    }
}
