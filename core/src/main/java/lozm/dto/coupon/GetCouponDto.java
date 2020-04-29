package lozm.dto.coupon;

import lombok.Getter;
import lombok.Setter;
import lozm.code.CouponType;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetCouponDto {

    private Long id;
    private String name;
    private String contents;
    private CouponType type;
    private Long amount;
    private Long quantity;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

//    public GetCouponDto(Long id, String name, String contents, CouponType type, Long amount) {
//        this.id = id;
//        this.name = name;
//        this.contents = contents;
//        this.type = type;
//        this.amount = amount;
//    }


    public GetCouponDto(Long id, String name, String contents, CouponType type, Long amount, Long quantity, LocalDateTime startDt, LocalDateTime endDt) {
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.type = type;
        this.amount = amount;
        this.quantity = quantity;
        this.startDt = startDt;
        this.endDt = endDt;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetCouponDto> list = new ArrayList<>();
    }

}
