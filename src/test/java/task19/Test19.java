package task19;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Test19 {
    @Test
    public void testParallel() {
        Matrix[] matrices = new Matrix[10];
        final int[][] f = {{1, 1}, {1, 0}};
        Arrays.parallelSetAll(matrices, i -> new Matrix(f));
        Arrays.parallelPrefix(matrices, (m1, m2) -> m1.multiply(m2));

        assertEquals(1, matrices[0].data[0][0]);
        assertEquals(2, matrices[1].data[0][0]);
        assertEquals(3, matrices[2].data[0][0]);
        assertEquals(5, matrices[3].data[0][0]);
        assertEquals(8, matrices[4].data[0][0]);
        assertEquals(13, matrices[5].data[0][0]);
        assertEquals(21, matrices[6].data[0][0]);
        assertEquals(34, matrices[7].data[0][0]);
        assertEquals(55, matrices[8].data[0][0]);
        assertEquals(89, matrices[9].data[0][0]);
    }
}
