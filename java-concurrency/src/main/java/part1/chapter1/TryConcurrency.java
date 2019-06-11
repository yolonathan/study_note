package part1.chapter1;

/**
 * @author Nathan
 */
public class TryConcurrency {


    public static void main(String[] args) {
        Thread t = new Thread("READ-Thread") {
            @Override
            public void run() {
                println(Thread.currentThread().getName());
                readFromDataBase();
            }
        };

        t.start();

        new Thread("WRITE-Thread") {
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }

    private static void readFromDataBase() {
        try {
            println("Begin read data from db");
            Thread.sleep(1000 * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }


    private static void println(String message) {
        System.out.println(message);
    }

    private static void writeDataToFile() {
        try {
            println("Begin write data to file.");
            Thread.sleep(2000 * 20L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }


}
