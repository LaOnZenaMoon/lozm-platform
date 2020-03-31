package lozm.core.dto.delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class PutDeliveryDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        private String name;

        private Long price;

        private Long quantity;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
