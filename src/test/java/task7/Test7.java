package task7;

import org.junit.Test;

import java.util.OptionalDouble;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Write a call to reduce that can be used to compute the average of a Stream<Double>.
 */
public class Test7 {
    @Test
    public void testAverage() {
        Stream<Double> streamOfDoubles = Stream.of(1.2, 4.3, 5.6);
        OptionalDouble average = streamOfDoubles
                .mapToDouble(Double::doubleValue)
                .average();
        assertEquals(3.69, average.getAsDouble(), 0.01);

    }
}
