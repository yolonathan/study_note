package test.lambda;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Jing Zhi Bao
 */
public class FunctionDemo {

    public static void main(String[] args) {
        Predicate<Integer> predicate = integer -> integer> 0;
        System.out.println(predicate.test(4));

        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("哈哈哈");
    }
}
