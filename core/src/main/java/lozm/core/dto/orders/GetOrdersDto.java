package lozm.core.dto.orders;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.OrderStatus;
import lozm.core.code.UserType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetOrdersDto {

    private Long id;
    private LocalDateTime orderDt;
    private OrderStatus status;

    public GetOrdersDto(Long id, LocalDateTime orderDt, OrderStatus status) {
        this.id = id;
        this.orderDt = orderDt;
        this.status = status;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetOrdersDto> list = new ArrayList<>();
    }

}
