package part1.produceconsumer;

import java.util.stream.Stream;

/**
 * 1. sleep
 *
 *    - 是Thread的方法
 *
 *    - 不释放资源（锁）
 *
 *    - 使用sleep不需要同步，不需要`monitor`
 *
 *    - 不需要唤醒
 *
 * 2. wait
 *
 *    - 是Object的方法
 *
 *    - 释放锁
 *
 *    - wait需要同步，需要`monitor`
 *
 *    - 需要被唤醒，加时间除外
 *
 *
 * @author Nathan
 */
public class DifferenceOfWaitAndSleep {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1", "T2").forEach(name ->
                new Thread(name) {
                    @Override
                    public void run() {
                        m2();
                    }
                }.start()
        );
    }

    public static void m1() {
        synchronized (LOCK) {
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter.");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter.");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
