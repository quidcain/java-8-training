package task1;

import org.junit.Test;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * Given an array of File objects, sort it so that the directories come before the files,
 * and within each group, elements are sorted by path name.
 * Use a lambda expression, not a Comparator.
 */
public class Test1 {
    @Test
    public void testSortFiles() {
        List<String> sorted = Stream.of("fileB", "dirB", "fileA", "dirA")
                .map((file) -> new File(getClass().getClassLoader().getResource(file).getFile()))
                .sorted(Comparator
                        .comparing(File::isDirectory)
                        .reversed()
                        .thenComparing(Comparator.comparing(File::getName)))
                .map(File::getName)
                .collect(toList());
        assertEquals(asList("dirA", "dirB", "fileA", "fileB"), sorted);
    }
}
