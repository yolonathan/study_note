package part1.daemon;

/**
 * @author Nathan
 */
public class DaemonThread {

    /**
     * 守护线程
     * 在Java中有两类线程：User Thread(用户线程)、Daemon Thread(守护线程)
     * <p>
     * 用个比较通俗的比如，任何一个守护线程都是整个JVM中所有非守护线程的保姆：
     * <p>
     * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
     * Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用就是 GC (垃圾回收器)，它就是一个很称职的守护者。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " running");
                Thread.sleep(100000);
                System.out.println(Thread.currentThread().getName() + " done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); //new
        t.start();
        t.setDaemon(true);
        //runnable  ->running| ->part1.dead| ->blocked


        //JDK1.7
        Thread.sleep(5_000);
        System.out.println(Thread.currentThread().getName());
    }
}

/**
 * 守护线程 发心跳
 * A<---------------------------------->B
 * ->daemonThread(health check)
 */
