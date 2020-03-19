package lozm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopAdminApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ShopAdminApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
