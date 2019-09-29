package com.github.nicejing.data.sort;

import java.util.Arrays;
import java.util.TooManyListenersException;

/**
 * @author Jing Zhi Bao
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        shellSort(arr);
    }


    /**
     * 插入版本
     * @param arr
     */
    private static void shellSort(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            int temp = 0;
            // 第一轮
            for (int i = gap; i < arr.length; i++) {
                // 共有五组 布长是5
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }

                }

            }
            System.out.println(Arrays.toString(arr));

        }


    }
}
