package lozm.core.dto.coupon;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PostCouponDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String name;

        @NotEmpty
        private String contents;

        @NotEmpty
        private String type;

        @NotNull
        private Long amount;

        @NotNull
        private Long quantity;

        @NotEmpty
        private LocalDateTime startDt;

        @NotEmpty
        private LocalDateTime endDt;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
