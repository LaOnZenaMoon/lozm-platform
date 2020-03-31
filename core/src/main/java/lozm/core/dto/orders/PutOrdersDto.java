package lozm.core.dto.orders;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.OrderStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PutOrdersDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        @NotEmpty
        private OrderStatus status;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
