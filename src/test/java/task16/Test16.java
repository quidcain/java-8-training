package task16;

import org.junit.Test;

import java.time.*;

import static org.junit.Assert.assertEquals;

public class Test16 {
    public static Duration getTripDuration(LocalTime departure, ZoneId departureZone, LocalTime arrival, ZoneId arrivalZone) {
        LocalDate now = LocalDate.now();
        Instant departureInstant;
        departureInstant = ZonedDateTime.of(now, departure, departureZone).toInstant();
        Instant arrivalInstant = ZonedDateTime.of(
                (arrival.isBefore(departure) || arrival.equals(departure)) ? now.plusDays(1) : now,
                arrival,
                arrivalZone
        ).toInstant();
        return Duration.between(departureInstant, arrivalInstant);
    }

    @Test
    public void test() {
        Duration duration = getTripDuration(
                LocalTime.of(14, 5),
                ZoneId.of("Europe/Paris"),
                LocalTime.of(16, 40),
                ZoneId.of("America/Los_Angeles")
        );
        assertEquals(11, duration.toHours());
        assertEquals(35, duration.toMinutes() % 60);
    }
}
