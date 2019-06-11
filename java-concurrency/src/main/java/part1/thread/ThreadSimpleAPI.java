package part1.thread;

import java.util.Optional;

/**
 * @author Nathan
 */
public class ThreadSimpleAPI {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            // Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t.start();
        // Name -> t1
        Optional.of(t.getName()).ifPresent(System.out::println);
        // Id -> 11
        Optional.of(t.getId()).ifPresent(System.out::println);
        // 优先级 -> 5
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }


}
