package com.github.nicejing.data.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author Jing Zhi Bao
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {134, 56 , 8 ,45, -3};
        insertSort(arr);

    }


    private static void insertSort(int[] arr){

        for(int i = 1; i < arr.length; i++){

            // 定义带待插入的位置
            int insertVal = arr[i];

            // 即 arr[1]的前面这个数的下标
            int insertIndex = i -1;

            // 给 insertValue 找打插入的位置
            // insertIndex> 0 不越界
            // insertVal < arr[insertIndex] 待插入的数，还没有找到插入的位置
            // 就需要将arr[insertIndex] 后移
            while (insertIndex>= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --;
            }
            // 当退出while循环时，说明插入的位置找到，insertIndex + 1

            arr[insertIndex + 1] = insertVal;
        }

        System.out.println(Arrays.toString(arr));




    }
}
