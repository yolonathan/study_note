package stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 并行流
 * @author Jing Zhi Bao
 */
public class StreamDemo5 {

    public static void main(String[] args) {
        // parallel 并行
        // long count = IntStream.rangeClosed(1, 100).parallel().peek(StreamDemo5::debug).count();

        // 先并行在串行， 多次调用parallel和sequential以最后一次为准

        // IntStream.rangeClosed(1, 100)
        //         // 并行
        //         .parallel().peek(StreamDemo5::debug)
        //         // 串行 sequential
        //         .sequential().peek(StreamDemo5::debug2)
        //         .count();

        // 使用自己的线程池，不使用默认的线程池， 防止任务被阻塞
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.rangeClosed(1, 100).parallel().peek(StreamDemo5::debug).count());

        pool.shutdown();

    }

    private static void debug(int i) {
        System.out.println("debug: " + i);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void debug2(int i) {
        System.err.println("debug: " + i);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
