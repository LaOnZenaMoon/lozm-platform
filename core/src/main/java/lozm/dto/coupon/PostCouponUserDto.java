package lozm.dto.coupon;

import lombok.Getter;
import lozm.dto.user.GetUserDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class PostCouponUserDto {

    @Getter
    public static class Request {
        @Size(min = 1)
        private List<GetUserDto> userList = new ArrayList<>();

        @NotEmpty
        private Long couponId;

        @NotEmpty
        private Long couponUserQuantity;
    }

}
