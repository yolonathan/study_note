package com.github.nicejing.data.queue;

import lombok.Data;

/**
 * @author Jing Zhi Bao
 */
@Data
public class ArrayQueue {

    /**
     * 表示队列的最大容量
     */
    private int maxSize;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 该数据用于存放数据, 模拟队列
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     *
     * @param arrMaxSize
     */
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];

        // 指向队列头部，分析出front 是指向队列头的前一个位置.
        front = -1;
        // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
        rear = -1;
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return front == maxSize - 1;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到队列
     *
     * @param n
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        // 让rear 后移
        rear++;
        arr[rear] = n;
    }

    public int popQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        front++; // front 后移
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front + 1];
    }
}
