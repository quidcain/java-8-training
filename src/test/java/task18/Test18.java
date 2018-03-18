package task18;

import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Test18 {
    @Test
    public void testReduceEntries() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(UUID.randomUUID().toString(), (long) new Random().nextInt(100));
        }

        map.forEach((k, v) -> System.out.println(k + "\t- " + v));

        Map.Entry<String, Long> stringLongEntry = map.reduceEntries(16, (prevEntry, currentEntry) -> {
            String key;
            if (currentEntry.getValue() > prevEntry.getValue()) {
                key = currentEntry.getKey();
            } else {
                key = prevEntry.getKey();
            }
            return new ConcurrentHashMap.SimpleEntry<>(key, Math.max(prevEntry.getValue(), currentEntry.getValue()));
        });

        System.out.println("final " + stringLongEntry.getKey() + "\t- " + stringLongEntry.getValue());
    }
}
