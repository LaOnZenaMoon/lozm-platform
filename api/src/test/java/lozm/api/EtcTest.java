package lozm.api;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

public class EtcTest {

    @Test
    void setRandomName() {
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        System.out.println("fullName = " + fullName);

        String identifier = faker.pokemon().name();
        System.out.println("identifier = " + identifier);
    }

    @Test
    public LocalDateTime getStartDt() {
        ZoneOffset kst = ZoneOffset.of("+9");

        long minDay = LocalDateTime.of(2014, 1, 1, 0, 0, 0).toEpochSecond(kst);
        long maxDay = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toEpochSecond(kst);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDateTime startDt = LocalDateTime.ofInstant(Instant.ofEpochSecond(randomDay), kst);
        System.out.println("startDt = " + startDt);
        
        return startDt;
    }

    @Test
    public void getEndDt() {
        LocalDateTime startDt = getStartDt();
        LocalDateTime endDt = startDt.plusYears(ThreadLocalRandom.current().nextLong(1, 2))
                .plusMonths(ThreadLocalRandom.current().nextLong(1, 31))
                .plusDays(ThreadLocalRandom.current().nextLong(1, 9))
                .plusHours(ThreadLocalRandom.current().nextLong(1, 12))
                .plusMinutes(ThreadLocalRandom.current().nextLong(1, 60))
                .plusSeconds(ThreadLocalRandom.current().nextLong(1, 60));
        System.out.println("endDt = " + endDt);
    }

}
