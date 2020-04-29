package lozm.dto.coupon;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class PostCouponUserDto {

    @Getter
    public static class Request {
        @NotNull
        private Long userId;

        @NotNull
        private Long couponId;

        @NotNull
        private Long couponUserQuantity;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
