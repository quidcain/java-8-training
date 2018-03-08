package task8;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Count all short words in a parallel Stream<String> , as described
 * int[] shortWords = new int[12];
 * words.parallel().forEach(
 * s -> { if (s.length() < 12) shortWords[s.length()]++; });
 * // Error—race condition!
 * System.out.println(Arrays.toString(shortWords));
 * by updating an array of AtomicInteger . Use the atomic getAndIncrement method to safely increment each counter.
 */
public class Test8 {
    @Test
    public void testParallelCount() {
        Stream<String> words = Stream
                .of("John Doe",
                    "Megh Tamhanakar",
                    "Purshottama Ashtikar",
                    "Max Lee",
                    "Matthew Dean",
                    "Dayananda Deshmukh");
        AtomicInteger[] shortWords = new AtomicInteger[12];
        for (int i = 0; i < 12; i++)
            shortWords[i] = new AtomicInteger();
        words.parallel().forEach(
                s -> { if (s.length() < 12) shortWords[s.length()].getAndIncrement(); });
        System.out.println(Arrays.toString(shortWords));
        assertEquals(1, shortWords[7].get());
        assertEquals(1, shortWords[8].get());
    }
}
