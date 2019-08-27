import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author Nathan
 */
public class DateTest {


    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2019, 7, 14);
        int year = localDate.getYear();
        System.out.println(year);

        Month month = localDate.getMonth();
        int value = month.getValue();
        System.out.println(value);

        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println(dayOfMonth);


        int dayOfYear = localDate.getDayOfYear();
        System.out.println(dayOfYear);

        int lengthOfMonth = localDate.lengthOfMonth();
        System.out.println(lengthOfMonth);

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println(dayOfWeek.getValue());


    }
}
