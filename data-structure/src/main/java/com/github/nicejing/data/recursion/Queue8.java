package com.github.nicejing.data.recursion;

/**
 * @author Jing Zhi Bao
 */
public class Queue8 {

    /**
     * 定义一个max 表示共有多少个皇后
     */
    private static int max = 8;
    /**
     * 定义数组array, 保存皇后放置位置的结果,比如arr = {0 , 4, 7, 5, 2, 6, 1, 3}
     */
    int[] array = new int[max];

    private static int count = 0;

    private static int judgeCount = 0;


    public static void main(String[] args) {
        //测试一把， 8 皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d 解法", count);
        System.out.printf("一共判断冲突的次数%d 次", judgeCount);
    }

    private void check(int n) {

        //n = 8 , 其实8 个皇后就既然放好
        if(n == max) {
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        // max = 8
        for(int i = 0; i < max; i++) {
            //先把当前这个皇后n , 放到该行的第1 列
            array[n] = i;
            //判断当放置第n 个皇后到i 列时，是否冲突

        }

    }


    /**
     * 打印棋盘
     * 写一个方法，可以将皇后摆放的位置输出
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
