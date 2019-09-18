package test.stream;

import java.util.stream.IntStream;

/**
 * @author Jing Zhi Bao
 */
public class StreamDemo1 {

    public static void main(String[] args) {
        int[] nums = { 1, 4, 6, 7, 45, 34 };
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println(sum);

        // map 是中间操作，返回流的操作
        int sum1 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
        System.out.println(sum1);

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);
    }

    public static int doubleNum(int i) {
        System.out.println(i + " * 2");
        return i * 2;
    }
}
