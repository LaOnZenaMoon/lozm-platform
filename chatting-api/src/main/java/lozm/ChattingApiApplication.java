package lozm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChattingApiApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ChattingApiApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
