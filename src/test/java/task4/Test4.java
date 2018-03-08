package task4;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * The characterStream method is was a bit clumsy. Write a stream-based one-liner instead.
 * public static Stream<Character> characterStream(String s) {
 *    List<Character> result = new ArrayList<>();
 *    for (char c : s.toCharArray()) result.add(c);
 *    return result.stream();
 * }
 */
public class Test4 {
    public static Stream<Character> characterStream(String s) {
        return s.chars().mapToObj((c) -> (char)c);
    }

    @Test
    public void testCharacterStream() {
        assertEquals(Arrays.asList('a', 'b', 'c'),
                characterStream("abc").collect(Collectors.toList()));
    }
}
