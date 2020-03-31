package lozm.core.dto.orders;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.OrderStatus;
import lozm.core.code.UserType;

import java.time.LocalDateTime;


public class GetOrdersDto {

    @Getter
    public static class Request {

    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private LocalDateTime orderDt;
        private OrderStatus status;

        public Response(Long id, LocalDateTime orderDt, OrderStatus status) {
            this.id = id;
            this.orderDt = orderDt;
            this.status = status;
        }

    }

}
