package lozm.core.dto.orders;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class PostOrdersDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String userId;

        @NotEmpty
        private String itemId;

        @NotEmpty
        private String deliveryId;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
