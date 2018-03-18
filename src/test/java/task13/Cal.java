package task13;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Write an equivalent of the Unix cal program that displays a calendar for a month.
 * For example, java Cal 3 2013 should display
 * 1 2  3
 * 4  5  6  7  8  9 10
 * 11 12 13 14 15 16 17
 * 18 19 20 21 22 23 24
 * 25 26 27 28 29 30 31
 * indicating that March 1 is a Friday. (Show the weekend at the end of the week.)
 */
public class Cal {
    public static void main(String[] args) {
        int month = 8;
        int year = 2013;
        LocalDate initial = LocalDate.of(year, month, 1);
        int numberOfPrecedingSpaces = (DayOfWeek.from(initial).getValue() - 1) * 3;
        String stringNumberOfPrecedingSpaces = String.valueOf(numberOfPrecedingSpaces);
        if (numberOfPrecedingSpaces == 0)
            stringNumberOfPrecedingSpaces = "";
        String precedingSpace = String.format("%" + stringNumberOfPrecedingSpaces + "c", ' ');
        String restPart = IntStream.range(1, initial.lengthOfMonth() + 1)
                .mapToObj(Integer::toString)
                .map((s) -> s.length() == 1 ? " " + s : s)
                .collect(Collectors.joining(" "));
        String calendarWithoutCarriageReturn = precedingSpace + restPart;
        String finalCalendar = calendarWithoutCarriageReturn.replaceAll("(.{21})", "$1\n");
        System.out.println(finalCalendar);
    }
}
