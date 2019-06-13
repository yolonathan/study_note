package part2.immutable;

import lombok.Getter;

/**
 * @author Nathan
 */
@Getter
public final class Person {

    private final String name;

    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
