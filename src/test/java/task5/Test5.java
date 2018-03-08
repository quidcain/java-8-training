package task5;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
 * that alternates elements from the streams first and second ,
 * stopping when one of them runs out of elements.
 */
public class Test5 {

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();
        Iterator<T> alternateIterator = new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                return firstIterator.hasNext() && secondIterator.hasNext();
            }

            @Override
            public T next() {
                if (index % 2 == 0) {
                    index++;
                    return firstIterator.next();
                } else {
                    index++;
                    return secondIterator.next();
                }
            }
        };
        return StreamSupport.stream(
                ((Iterable<T>)() -> alternateIterator).spliterator(), false);
    }

    @Test
    public void testZip() {
        Stream<Integer> odd = IntStream.iterate(1, x -> x + 2).limit(10).boxed();
        Stream<Integer> even = IntStream.iterate(2, x -> x + 2).limit(10).boxed();
        List<Integer> actual = zip(odd, even).collect(toList());
        List<Integer> expected = IntStream.rangeClosed(1, 19).boxed().collect(toList());
        assertEquals(expected, actual);
    }
}
