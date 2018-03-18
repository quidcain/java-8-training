package task15;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;

/**
 * Turning ZoneId.getAvailableIds into a stream
 * and using stream operations, find all time zones
 * whose offsets aren’t full.
 */
public class Test15 {

    @Test
    public void testNotFullTimeZones() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter((z) -> {
                    ZoneOffset offset = LocalDateTime.now().atZone(z).getOffset();
                    return offset.getTotalSeconds() % 3600 != 0;
                })
                //.map((z) -> LocalDateTime.now().atZone(z).getOffset())
                .forEach(System.out::println);
    }
}
