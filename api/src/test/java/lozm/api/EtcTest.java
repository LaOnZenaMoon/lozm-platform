package lozm.api;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class EtcTest {

    @Test
    void setRandomName() {
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        System.out.println("fullName = " + fullName);

        String identifier = faker.pokemon().name();
        System.out.println("identifier = " + identifier);
    }

}
