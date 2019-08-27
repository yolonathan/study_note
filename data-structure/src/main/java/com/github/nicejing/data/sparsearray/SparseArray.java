package com.github.nicejing.data.sparsearray;

/**
 * 稀疏数组
 *
 * @author Jing Zhi Bao
 */
public class SparseArray {

    public static void main(String[] args) {

        int[][] chessArray = new int[11][11];
        // 0 表示没有棋子 1表示黑子 2 表示白子
        chessArray[1][2] = 1;
        chessArray[2][3] = 1;
        chessArray[4][5] = 2;

        System.out.println("原始数组：");
        for (int[] chess : chessArray) {
            for (int i : chess) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        // 一共有多少有效数据
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0 的值存放到sparseArr 中
        // count 用于记录是第几个非0 数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray[i][j];
                }
            }
        }


        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        System.out.println();

        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for(int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
