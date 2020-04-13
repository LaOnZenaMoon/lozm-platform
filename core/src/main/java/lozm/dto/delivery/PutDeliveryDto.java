package lozm.dto.delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class PutDeliveryDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        private String country;

        private String zipCode;

        private String city;

        private String street;

        private String etc;

        private String status;

        private int flag;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
