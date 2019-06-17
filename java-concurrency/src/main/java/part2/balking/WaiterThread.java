package part2.balking;

/**
 * @author Nathan
 */
public class WaiterThread extends Thread {

    private final BalkingData balkingData;

    public WaiterThread(BalkingData balkingData) {
        super("Waiter");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            try {
                balkingData.save();
                Thread.sleep(1_000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
