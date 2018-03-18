package task17;

import java.io.File;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordToFilesMapper {
    private static int N_THREADS = 2;
    private ExecutorService executorService;
    private List<File> files;
    private ConcurrentHashMap<String, Set<File>> stringToFileMap = new ConcurrentHashMap<>();


    public WordToFilesMapper() {
        executorService = Executors.newFixedThreadPool(N_THREADS);
        files = Stream.of("fileB", "dirB/fileC", "fileA", "dirA/fileD")
                .map((file) -> new File(getClass().getClassLoader().getResource(file).getFile()))
                .collect(Collectors.toList());
    }

    public void map() throws InterruptedException {
        List<Callable<Void>> collect = files.stream().map(file -> (Callable<Void>) () -> {
            Set<File> fileSet = ConcurrentHashMap.newKeySet();
            Files.lines(file.toPath()).forEach(line -> {
                String[] words = line.split(" ");
                for (String word : words) {
                    System.out.println("Word " + word + " is in " + file.getName());
                    fileSet.add(file);
                    getStringToFileMap().merge(word, fileSet, (oldSet, newSet) -> {
                        Set<File> temp = new HashSet<>();
                        temp.addAll(newSet);
                        temp.addAll(oldSet);
                        return temp;
                    });
                }
            });
            return null;
        }).collect(Collectors.toList());
        executorService.invokeAll(collect);
        System.out.println("Hashmap size = "+ getStringToFileMap().size());
        executorService.shutdown();
    }

    public List<File> getFiles() {
        return files;
    }

    public ConcurrentHashMap<String, Set<File>> getStringToFileMap() {
        return stringToFileMap;
    }

    public void setStringToFileMap(ConcurrentHashMap<String, Set<File>> stringToFileMap) {
        this.stringToFileMap = stringToFileMap;
    }
}
