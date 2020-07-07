package lozm.object.dto.orders;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteOrdersDto {

    private Long ordersId;
    private int flag;

    @Getter
    public static class Request extends BaseUserDto {
        @Size(min = 1)
        private List<DeleteOrdersDto> list = new ArrayList<>();
    }

}
