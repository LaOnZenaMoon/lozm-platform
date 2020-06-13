package lozm.object.dto.coupon;

import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DeleteCouponUserDto {

    @Getter
    public static class Request {
        @Size(min = 1)
        private List<GetCouponUserDto> list = new ArrayList<>();
    }

}
