package test.lambda;

import java.util.function.Function;

/**
 * 级联表达式和柯里化 函数标准化 返回函数的函数
 * @author Jing Zhi Bao
 */
public class CurryDemo {

    public static void main(String[] args) {
        // 级联表达式
        Function<Integer, Function<Integer, Integer>> fun = x -> y -> x + y;
        // 柯里化 把多个参数的函数转化为只有一个参数的函数
        System.out.println(fun.apply(4).apply(5));
    }
}
