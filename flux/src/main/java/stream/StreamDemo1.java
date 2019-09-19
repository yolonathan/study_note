package stream;

import java.util.stream.IntStream;

/**
 * @author Jing Zhi Bao
 */
public class StreamDemo1 {

    public static void main(String[] args) {
        int[] nums = {1, 4, 6, 45};
        int sum = 0;
        // 外部迭代
        for (int i : nums) {
            sum += i;
        }
        System.out.println(sum);

        // 中间操作返回流
        // 内部迭代
        int sum1 = IntStream.of(nums).map(i -> i * 2).sum();
        System.out.println(sum1);
    }
}
