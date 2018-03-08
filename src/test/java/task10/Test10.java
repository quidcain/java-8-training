package task10;

import org.junit.Test;

import java.util.Comparator;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;

/**
 * Write a method that generates a Comparator<String> that can be normal or reversed,
 * case-sensitive or case-insensitive, space-sensitive or space-insensitive,
 * or any combination thereof. Your method should return a lambda expression.
 */
public class Test10 {
    public static Comparator<String> createComparator(EnumSet<Mode> flags) {
        return (a, b) -> {
            if (flags.contains(Mode.CASE_INSENSITIVE)) {
                a = a.toLowerCase();
                b = b.toLowerCase();
            }
            if (flags.contains(Mode.SPACE_INSENSITIVE)) {
                a = a.replaceAll("\\s+", "");
                b = a.replaceAll("\\s+", "");
            }
            if (flags.contains(Mode.REVERSED))
                return b.compareTo(a);
            else
                return a.compareTo(b);
        };
    }

    @Test
    public void testComparator() {
        Comparator<String> comparator = createComparator(EnumSet.allOf(Mode.class));
        assertEquals(0, comparator.compare("A ", "a"));
    }
}
