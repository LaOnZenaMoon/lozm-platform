package lozm.core.dto.coupon;

import lombok.Getter;
import lombok.Setter;
import lozm.core.dto.item.PostItemDto;

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

        @NotNull
        private LocalDateTime startDt;

        @NotNull
        private LocalDateTime endDt;

        public static Request setRequestTestData(String name, String contents, String type, Long amount, Long quantity, LocalDateTime startDt, LocalDateTime endDt) {
            PostCouponDto.Request reqDto = new PostCouponDto.Request();
            reqDto.name = name;
            reqDto.contents = contents;
            reqDto.type = type;
            reqDto.amount = amount;
            reqDto.quantity = quantity;
            reqDto.startDt = startDt;
            reqDto.endDt = endDt;

            return reqDto;
        }
    }

    @Getter
    @Setter
    public static class Response {

    }

}
