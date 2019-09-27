package com.github.nicejing.data.sort;

import java.util.Arrays;

/**
 * @author Jing Zhi Bao
 */
public class BubbleSort1 {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        int temp = 0;

        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序的数组");
            System.out.println(Arrays.toString(arr));
            if (!flag) {
                break;
            }

            flag = false;
        }
    }
}
