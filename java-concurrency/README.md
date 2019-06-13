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



## Wait Set的概念

`WaitSet` in synchronized monitor

“线程休息室”

- 所有的对象都会有一个wait set,用来存放调用了该对象wait方法之后进入block状态线程

* 2.线程被notify之后，不一定立即得到执行
* 3.线程从wait set中被唤醒顺序不一定是FIFO.
* 4.线程被唤醒后，必须重新获取锁

## Volatile 关键字

volatile关键字的两层语义 一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义

- 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的
- 禁止进行指令重排序

```java
// 线程1
boolean stop = false;
while(!stop){
    doSomething();
}
 
// 线程2
stop = true;
```

用volatile修饰之后：

- 使用volatile关键字会强制将修改的值立即写入主存
- 使用volatile关键字的话，当线程2进行修改时，会导致线程1的工作内存中缓存变量stop的缓存行无效（反映到硬件层的话，就是CPU的`L1`或者`L2`缓存中对应的缓存行无效）
- 由于线程1的工作内存中缓存变量stop的缓存行无效，所以线程1再次读取变量stop的值时会去主存读取

### volatile与原子性

```java
public class Test {
    public volatile int inc = 0;
     
    public void increase() {
        inc++;
    }
     
    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
         
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
```

运行它会发现每次运行结果都不一致，都是一个小于10000的数字

**自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的**



### volatile与有序性

在前面提到volatile关键字能禁止指令重排序，所以volatile能在一定程度上保证有序性

volatile关键字禁止指令重排序有两层意思：

- 当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见；在其后面的操作肯定还没有进行
- 在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句放到其前面执行

```java
//x、y为非volatile变量
//flag为volatile变量
 
x = 2;        //语句1
y = 0;        //语句2
flag = true;  //语句3
x = 4;         //语句4
y = -1;       //语句5
```

由于flag变量为volatile变量，那么在进行指令重排序的过程的时候，不会将语句3放到语句1、语句2前面，也不会讲语句3放到语句4、语句5后面。但是要注意语句1和语句2的顺序、语句4和语句5的顺序是不作任何保证的。

并且volatile关键字能保证，执行到语句3时，语句1和语句2必定是执行完毕了的，且语句1和语句2的执行结果对语句3、语句4、语句5是可见的



### volatile的原理和实现机制

如何保证可见性和禁止指令重排序的？

《深入理解Java虚拟机》

> 观察加入volatile关键字和没有加入volatile关键字时所生成的汇编代码发现，加入volatile关键字时，会多出一个lock前缀指令

lock前缀指令实际上相当于一个内存屏障（也成内存栅栏），内存屏障会提供3个功能：

- 它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，在它前面的操作已经全部完成
- 它会强制将对缓存的修改操作立即写入主存
- 如果是写操作，它会导致其他CPU中对应的缓存行无效

### volatile与synchronized

volatile关键字是无法替代synchronized关键字的，因为volatile关键字无法保证操作的原子性

通常来说，使用volatile必须具备以下2个条件：

- 对变量的写操作不依赖于当前值
- 该变量没有包含在具有其他变量的不变式中

实际上，这些条件表明，可以被写入 volatile 变量的这些有效值独立于任何程序的状态，包括变量的当前状态

事实上，我的理解就是上面的2个条件需要保证操作是原子性操作，才能保证使用volatile关键字的程序在并发时能够正确执行



1. 原子性
2. 可见性
3. 有序性

### happens-before规则

- 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作
- 锁定规则：一个unLock操作先行发生于后面对同一个锁额lock操作
- volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
- 传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C
- 线程启动规则：Thread对象的start()方法先行发生于此线程的每个一个动作
- 线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
- 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
- 对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始



### 使用场景

- 状态量标记
- 屏障前后的一致性



## 观察者设计模式

`Observer`

代码：`part2.observer`

订阅一个主题，当主题发生变化后，通知到观察者

### 多线程中的观察者模式

代码：`part2.observer.thread`

## 多线程读写锁分离

手写读写锁



## 不可变对象设计模式

不可变对象是线程安全的

可变对象不一定是不安全的

`servlet`不是线程安全的

`struts 1` Action 不是线程安全的

`struts 2` 是线程安全的



## Future模式

- Future ：代表的是未来的一个凭据

* FutureTask：将你的调用逻辑进行了隔离
* FutureService：桥接 Future和 FutureTask





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
