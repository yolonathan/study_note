package part2.immutable;

import java.util.stream.IntStream;

/**
 * @author Nathan
 */
public class ImmutableClient {

    public static void main(String[] args) {

        // Share data
        Person person = new Person("Nathan", "Bob");
        IntStream.range(0, 5).forEach(i ->
                new UsePersonThread(person).start()
        );
    }
}
