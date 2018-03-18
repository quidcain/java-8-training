package task12;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static org.junit.Assert.assertEquals;

/**
 * Compute Programmer’s Day without using plusDays.
 */
public class Test12 {
    @Test
    public void testProgrammersDay() {
        LocalDate localDate = LocalDate.now().withDayOfYear(255);
        System.out.println(localDate);
        assertEquals(LocalDate.now().with(firstDayOfYear()).plusDays(254), localDate);
    }
}
