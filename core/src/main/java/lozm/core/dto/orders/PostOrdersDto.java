package lozm.core.dto.orders;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class PostOrdersDto {

    @Getter
    public static class Request {
        @NotNull
        private Long orderedPrice;

        @NotNull
        private Long quantity;

        @NotNull
        private Long userId;

        @NotNull
        private Long itemId;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
