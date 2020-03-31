package lozm.core.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.DeliveryStatus;

import javax.validation.constraints.NotEmpty;
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

        private DeliveryStatus status;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
