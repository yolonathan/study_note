package part2.future;

/**
 * @author Nathan
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "SUCCESS";
        });

        System.out.println("do other something.");
        System.out.println(future.get());
    }

}
