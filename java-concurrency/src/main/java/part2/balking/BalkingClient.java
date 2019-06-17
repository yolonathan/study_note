package part2.balking;

/**
 * @author Nathan
 */
public class BalkingClient {
    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData("C:\\Users\\Nathan\\IdeaProjects\\java-concurrency\\design_pattern\\balking.txt", "===BEGIN====");
        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();
    }
}
