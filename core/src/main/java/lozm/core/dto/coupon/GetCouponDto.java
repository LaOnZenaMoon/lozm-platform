package lozm.core.dto.coupon;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.CouponType;


public class GetCouponDto {

    @Getter
    public static class Request {

    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String name;
        private String contents;
        private CouponType type;
        private Long amount;

        public Response(Long id, String name, String contents, CouponType type, Long amount) {
            this.id = id;
            this.name = name;
            this.contents = contents;
            this.type = type;
            this.amount = amount;
        }
    }

}
