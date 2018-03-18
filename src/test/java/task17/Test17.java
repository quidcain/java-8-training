package task17;

import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class Test17 {
    @Test
    public void testMapper() throws InterruptedException {
        WordToFilesMapper wordToFilesMapper = new WordToFilesMapper();
        wordToFilesMapper.getFiles();
        wordToFilesMapper.map();
        for (Map.Entry<String, Set<File>> entry : wordToFilesMapper.getStringToFileMap().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
