package lozm.dto.coupon;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class PutCouponUserDto {

    @Getter
    public static class Request {
        @Size(min = 1)
        private List<GetCouponUserDto> list = new ArrayList<>();

        @NotNull
        private Long couponUserQuantity;
    }

}
