package lambda;

import java.text.DecimalFormat;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Jing Zhi Bao
 */
public class MoneyDemo {

    public static void main(String[] args) {

        MyMoney myMoney = new MyMoney(100);
        Function<Integer, String> function = i -> new DecimalFormat("#,###").format(i);
        myMoney.printMoney(function.andThen(s -> "人民币" + s));

    }
}


// interface IMoneyFormat {
//
//     /**
//      * 格式化
//      *
//      * @param i
//      * @return
//      */
//     String format(int i);
// }

class MyMoney {

    private final int money;

    MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> function) {
        System.out.println("我的存款: " + function.apply(money));
    }

}


