package part2.immutable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nathan
 */
public class ImmutableTest {

    private final int age;
    private final String name;
    private final List<String> list;


    public ImmutableTest(int age, String name) {
        this.age = age;
        this.name = name;
        list = new ArrayList<>();
    }


}
