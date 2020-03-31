package lozm.core.dto.item;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.OrderStatus;

import java.time.LocalDateTime;


public class GetItemDto {

    @Getter
    public static class Request {

    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String name;
        private Long price;
        private Long quantity;

        public Response(Long id, String name, Long price, Long quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

}
