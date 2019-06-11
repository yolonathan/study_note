package part1.stop;

import part1.thread.ThreadService;

/**
 * @author Nathan
 */
public class StopThread2 {


    public static void main(String[] args) {

        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
            // load a very heavy resource.
            while (true) {

            }
            // try {
            //     Thread.sleep(5000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        });
        service.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
