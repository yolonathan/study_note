package lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

/**
 * @author Jing Zhi Bao
 */
public class MethodReferenceDemo {

    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        consumer.accept("My");

        // 静态方法引用
        Consumer<Dog> consumer1 = Dog::bark;
        consumer1.accept(new Dog());

        // 实例对象引用
        // Function<Integer, Integer> function = new Dog()::eat;
        // UnaryOperator<Integer> function = new Dog()::eat;
        IntUnaryOperator function = new Dog()::eat;;
        Integer apply = function.applyAsInt(4);
        System.out.println(apply);


        // 构造函数的引用
        Supplier<Dog>  dog = Dog::new;
        Dog dog1 = dog.get();

        Function<String, Dog> dogStringFunction = Dog::new;
        Dog aaa = dogStringFunction.apply("aaa");



    }
}

class Dog{

    private String name ="哮天犬";

    public Dog(String name) {
        this.name = name;
    }

    public Dog() {
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void bark(Dog dog){
        System.out.println(dog + "叫了");

    }

    public int eat(Dog this, int i){
        return 4;
    }


}
