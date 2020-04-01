package lozm.core.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostUserCouponDto {

    @Getter
    public static class Request {
        @NotNull
        private Long userId;

        @NotNull
        private Long couponId;

        @NotNull
        private Long couponQuantity;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
