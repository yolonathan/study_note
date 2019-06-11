# 多线程基础知识

- Java多线程介绍
- 多线程变成入门
- 线程创建于启动以及线程状态

![](http://ww1.sinaimg.cn/large/006w8oYnly1g3w7164p25j30qu08s3z2.jpg)

```java
public enum State {
        /**
         * Thread state for a thread which has not yet started.
         */
        NEW,

        /**
         * Thread state for a runnable thread.  A thread in the runnable
         * state is executing in the Java virtual machine but it may
         * be waiting for other resources from the operating system
         * such as processor.
         */
        RUNNABLE,

        /**
         * Thread state for a thread blocked waiting for a monitor lock.
         * A thread in the blocked state is waiting for a monitor lock
         *part1.sync1.sync
 part1.sync  part1.sync{@link Object#wait() Object.waisync */
        BLOCKED,

        /**
         * Thread state for a waiting thread.
         * A thread is in the waiting state due to calling one of the
         * following methods:
         * <ul>
         *   <li>{@link Object#wait() Object.wait} with no timeout</li>
         *   <li>{@link #part1.join() part1.jpart1.join with no timeout</li>
         *   <li>{@link LockSupport#park() LockSupport.park}</li>
         * </ul>
         *
         * <p>A thread in the waiting state is waiting for another thread to
         * perform a particular action.
         *
         * For example, a thread that has called <tt>Object.wait()</tt>
         * on an object is waiting for another thread to call
         *produceconsumer
         *part1.join
         * is waiting for a specified thpart1.join to terminate.
         */
        WAITING,

        /**
         * Thread state for a waiting thread with a specified waiting time.
         * A thread is in the timed waiting state due to calling one of
         * the following methods with a specified positive waiting time:
         * <ul>
         *   <li>{@link #sleep Thread.sleep}</li>
         *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
         *   <li>{@link #join(long) part1.join} with timeout</li>
         * part1.joini>{@link LockSpart1.joinrt#parkNanos LockSupport.parkNanos}</li>
         *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
         * </ul>
         */
        TIMED_WAITING,

        /**
         * Thread state for a terminated thread.
         * The thread has completed execution.
         */
        TERMINATED;
    }
```



- Runnable接口详细详解

1. 创建线程对象Thread，默认有一个线程名， 以Thread-开头，从0开始计数
   1. Thread-0
   2. Thread-1
2. 如果在构造Thread的时候，没有传递Runnable或者没有复写Thread的run方法，该Thread不会调用任何东西，如果传递了Runnable接口的实例，或者复写了Thread的run方法，则会执行该方法的逻辑单元
3. 如果构造线程对象未传入`ThreadGroup`，`Thread`会默认获取父线程的`ThreadGroup`作为该线程的`ThreadGroup`，次时子线程和父线程将会在同一个`ThreadGroup`
4. 构造Thread的时候传入`stackSize`带包这该线程占用stack大小，如果没有指定stackSize的大小，默认是0，0代表会忽该参数，改参数会被JNI函数去使用。需要注意：该参数有一些平台有效，在有些平台则无效
   1. 用 `JVM`参数  `-Xss10M` 去设置

```java
	public Thread() {
        init(null, null, "Thread-" + nextThreadNum(), 0);
    }

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup,Runnable,String) Thread}
     * {@code (null, target, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     *
     * @param  target
     *         the object whose {@code run} method is invoked when this thread
     *         is started. If {@code null}, this classes {@code run} method does
     *         nothing.
     */
    public Thread(Runnable target) {
        init(null, target, "Thread-" + nextThreadNum(), 0);
    }
```



- 线程同步
  - `This` 锁 使用本实例作为锁
  - `Class`锁
- 线程间通讯

`wait`,`notify`,`notifyAll`

1. sleep

   - 是Thread的方法

   - 不释放资源（锁）

   - 使用sleep不需要同步，不需要`monitor`

   - 不需要唤醒

2. wait

   - 是Object的方法

   - 释放锁

   - wait需要同步，需要`monitor`

   - 需要被唤醒，加时间除外

- 线程组详解

可以访问自己的线程组，可以访问父线程组的可读属性

```java
private ThreadGroup() {     // called from C code
    this.name = "system";
    this.maxPriority = Thread.MAX_PRIORITY;
    this.parent = null;
}

public ThreadGroup(String name) {
    this(Thread.currentThread().getThreadGroup(), name);
}

public ThreadGroup(ThreadGroup parent, String name) {
    this(checkParentAccess(parent), parent, name);
}
```



- 自运行对象详解
- 线程异常回调
- 线程池详解
- 等待线程完成任务
- 阻塞IO和多sync
  sync- 自定义线程锁详解
- FIFO队列与线程
- 多线程`API`查缺补漏

# 多线程设计模式详细介绍

- `WaitSet`概念介绍
- 多线程程序衡量标准讨论
- Single Thread Execution 模式介绍
- 不可变对象以及线程安全对象介绍
- Guraded Suspension模式
- Balking 模式详细介绍
- Producer-Consumer设计模式详细介绍
- 读写锁设计模式详细介绍
- Thread-Per-Message模式详细介绍
- Worker模式详细介绍
- Future设计模式详细介绍
- Two-Phase Termination设计模式详细介绍
- Thread-Specific Storage模式介绍
- Active Object 接受异步消息的主动对象
- 设计模式差缺补漏

# JDK并发包详细介绍

- 原子变量
- Unsafe
- CountDownLatch
- CyclicBarrier
- Exchanger
- ExecutorService
- Phaser
- 显式锁
  - ReetrantLock
  - ReadWriteLock
  - StampedLock
- Condition
- Semaphore信号量
- ForkJoin
- Concurrent容器
  - ConcurrentHashMap
  - ConcurrentLinkedDeque
  - ConcurrentSkipListMap
  - ConcurrentSkipSet
  - CopyOnWriteArraySet
  - DelayQueue
  - LinkedBlockingQueue
  - LinkedTransferQueue
  - PriorityBlockingQueue
- CompletableFuture
- 自定义并发类
  - 自定义ThreadPoolExecutor
  - 实现一个优先级线程池
  - ThreadFactory
  - 自定义Lock
  - 自定义原子对象

## 单例模式

### 饿汉式

```java
public class SingletonObject1 {

    /**
     * can't lazy load.
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {
        //empty
    }

    public static SingletonObject1 getInstance() {
        return instance;
    }
}
```

### 懒汉式

**在多线程下会有并发问题**

```java
public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2() {
        //empty
    }

    public static SingletonObject2 getInstance() {
        if (null == instance)
            instance = new SingletonObject2();

        return SingletonObject2.instance;
    }
}
```

### 懒汉式-加锁

`getInstance`变成串行，效率低 

```java
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3() {
        //empty
    }

    public synchronized static SingletonObject3 getInstance() {

        if (null == instance) {
            instance = new SingletonObject3();
        }

        return SingletonObject3.instance;
    }
}
```

### 懒汉式-双检查

可能会引发**空指针异常**，可能还没有初始化完成就开始访问了

```java
public class SingletonObject4 {

    private static SingletonObject4 instance;

    private SingletonObject4() {}

    //double check
    public static SingletonObject4 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    instance = new SingletonObject4();
                }
            }
        }

        return SingletonObject4.instance;
    }
}
```

### 懒汉式-双检查和可见性(防止重排序)

```java
public class SingletonObject5 {

    private static volatile SingletonObject5 instance;

    private SingletonObject5() {}

    //double check add volatile
    public static SingletonObject5 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    instance = new SingletonObject5();
                }
            }
        }
        return SingletonObject5.instance;
    }
}
```

### Holder的方式

线程安全，懒加载，效率高，不需要锁

```java
public class SingletonObject6 {

    private SingletonObject6() {

    }

    private static class InstanceHolder {
      	// 只执行一次
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }
}
```

### 枚举

```java
public class SingletonObject7 {
    private SingletonObject7() {

    }

    private enum Singleton {
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }

    public static SingletonObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
```



# 并发编程深入探讨

- 死锁诊断，JVM工具，线程堆栈介绍
- 线程安全性探讨
- 数据共享，以及数据共享带来的安全隐患
- 构建线程安全的类，选择优化策略
- 构建并行模块基础详解
- 执行并行任务详细介绍
- 任务的执行与关闭
- 线程池的优化
- 线程上下文，性能，可伸缩性探讨
- 多线程中锁详细探讨
- 多线程中的锁详解
- 构建同步工具
- 原子变量与非阻塞同步机制
- Google concurrent包介绍
- Google EventBus
