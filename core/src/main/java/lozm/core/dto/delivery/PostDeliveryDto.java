package lozm.core.dto.delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostDeliveryDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String name;

        @NotNull
        private Long price;

        @NotNull
        private Long quantity;

        @NotEmpty
        private String type;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
