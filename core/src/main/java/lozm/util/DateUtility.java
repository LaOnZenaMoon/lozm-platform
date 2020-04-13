package lozm.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtility {

    private static ZoneOffset KST = ZoneOffset.of("+9");

    public static LocalDateTime getStartDt(ZoneOffset zos) {
        if(zos == null) zos = KST;

        long minDay = LocalDateTime.of(2014, 1, 1, 0, 0, 0).toEpochSecond(zos);
        long maxDay = LocalDateTime.of(2030, 12, 31, 23, 59, 59).toEpochSecond(zos);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDateTime startDt = LocalDateTime.ofInstant(Instant.ofEpochSecond(randomDay), zos);

        return startDt;
    }

    public static LocalDateTime getEndDt(LocalDateTime startDt) {
        return startDt.plusYears(ThreadLocalRandom.current().nextInt(0, 2))
                .plusMonths(ThreadLocalRandom.current().nextInt(0, 12))
                .plusDays(ThreadLocalRandom.current().nextInt(0, 31))
                .plusHours(ThreadLocalRandom.current().nextInt(0, 12))
                .plusMinutes(ThreadLocalRandom.current().nextInt(0, 60))
                .plusSeconds(ThreadLocalRandom.current().nextInt(0, 60));
    }

}
