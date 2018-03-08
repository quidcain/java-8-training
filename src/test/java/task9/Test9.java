package task9;

import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Repeat the preceding exercise, but filter out the short strings
 * and use the collect method with Collectors.groupingBy and Collectors.counting.
 */
public class Test9 {
    @Test
    public void testParallelCount() {
        Stream<String> words = Stream
                .of("John Doe",
                    "Megh Tamhanakar",
                    "Purshottama Ashtikar",
                    "Max Lee",
                    "Dayananda Deshmukhhh");
        Map<Integer, Long> collect = words.parallel()
                .filter(s -> s.length() >= 12)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(collect);
        assertEquals(2, (long)collect.get(20));
        assertEquals(1, (long)collect.get(15));
    }
}
