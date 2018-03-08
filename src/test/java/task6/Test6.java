package task6;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Join all elements in a Stream<ArrayList<T>> to one ArrayList<T> with reduce.
 */
public class Test6 {
    @Test
    public void testFlatMap() {
        Stream<ArrayList<Integer>> arrayListStream = Stream.of(2, 4, 6)
                .map((e) -> new ArrayList<>(Collections.singletonList(e)));
        ArrayList<Integer> integers = arrayListStream.flatMap(ArrayList::stream)
                .collect(Collectors.toCollection(ArrayList::new));
        assertEquals(Arrays.asList(2, 4 , 6), integers);
    }
}
