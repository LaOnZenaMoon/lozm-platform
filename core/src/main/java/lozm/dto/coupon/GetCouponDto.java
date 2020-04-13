package lozm.dto.coupon;

import lombok.Getter;
import lombok.Setter;
import lozm.code.CouponType;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetCouponDto {

    private Long id;
    private String name;
    private String contents;
    private CouponType type;
    private Long amount;

    public GetCouponDto(Long id, String name, String contents, CouponType type, Long amount) {
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.type = type;
        this.amount = amount;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetCouponDto> list = new ArrayList<>();
    }

}
