package lozm.core.dto.orders;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class PostOrdersDto {

    @Getter
    public static class Request {
        private Long orderedPrice;

        private int count;

        @NotEmpty
        private Long userId;

        @NotEmpty
        private Long itemId;

        @NotEmpty
        private Long deliveryId;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
