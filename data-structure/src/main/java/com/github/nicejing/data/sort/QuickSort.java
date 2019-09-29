package com.github.nicejing.data.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author Jing Zhi Bao
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -576, 70};
        quickSort(arr, 0, 5);


    }

    public static void quickSort(int[] arr, int left, int rigth) {
        // 左下标
        int l = left;
        // 右下标
        int r = rigth;
        int pivot = arr[(left + rigth) / 2];
        int temp = 0;

        // while 循环的额目的是让比 pivot 值小的放到左边
        // 比 povot 大的值放右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于pivot值，才推出
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            if( l >= r){
                 break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;


            // 如果交换结束后 arr[l] ==  pivot 相等 r-- 前移
            if(arr[l] == pivot){
                r -= 1;
            }

            if(arr[l] ==pivot){
                l +=1;
            }

        }

        System.out.println(Arrays.toString(arr));

        if (l == r) {
            l +=1;
            r -=1;
        }

        // 向左递归
        if(left < r){
            quickSort(arr, left, r);
        }
        if(rigth > l){
            quickSort(arr, l, rigth);
        }

    }
}
