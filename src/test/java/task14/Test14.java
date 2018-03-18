package task14;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Write a program that prints how many days you have been alive.
 */
public class Test14 {
    @Test
    public void testPassedDays() {
        LocalDate myBirthDate = LocalDate.of(1998, 2, 1);
        LocalDate now = LocalDate.now();
        long between = DAYS.between(myBirthDate, now);
        System.out.println(between);
    }
}
