package lozm.core.dto.delivery;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.DeliveryStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostDeliveryDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String country;

        @NotEmpty
        private String zipCode;

        @NotEmpty
        private String city;

        @NotEmpty
        private String street;

        private String etc;

        private String status;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
