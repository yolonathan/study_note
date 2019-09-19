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
        if (n == max) {
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        // max = 8
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n , 放到该行的第1 列
            array[n] = i;
            //判断当放置第n 个皇后到i 列时，是否冲突
            // 不冲突
            if (judge(n)) {
                //接着放n+1 个皇后,即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i; 即将第n 个皇后，放置在本行得后移的一个位置
        }

    }

    /**
     * @param n 表示第n 个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 说明
            //1. array[i] == array[n] 表示判断第n 个皇后是否和前面的n-1 个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n 个皇后是否和第i 皇后是否在同一斜线
            // n = 1 放置第2 列1 n = 1 array[1] = 1
            // Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;

            }
        }
        return true;
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
        System.out.println("==============");
    }
}
