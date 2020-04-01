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
    public void getStartDt() {
        ZoneOffset kst = ZoneOffset.of("+9");

        long minDay = LocalDateTime.of(2014, 1, 1, 0, 0, 0).toEpochSecond(kst);
        long maxDay = LocalDateTime.of(2020, 12, 31, 23, 59, 59).toEpochSecond(kst);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDateTime randomDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(randomDay), kst);
        System.out.println("randomDate = " + randomDate);

        LocalDateTime sysDateTime = LocalDateTime.now(kst);
        System.out.println("sysDateTime = " + sysDateTime);
    }

    public void getEndDt() {

    }

}
