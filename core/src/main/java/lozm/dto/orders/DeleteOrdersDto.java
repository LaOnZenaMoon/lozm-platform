package lozm.dto.orders;

import lombok.Getter;
import lozm.code.OrderStatus;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteOrdersDto {

    private Long ordersId;
    private int flag;

    @Getter
    public static class Request {
        @Size(min = 1)
        private List<DeleteOrdersDto> list = new ArrayList<>();
    }

}
