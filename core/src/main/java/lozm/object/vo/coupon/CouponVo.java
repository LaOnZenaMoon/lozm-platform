package lozm.object.vo.coupon;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;

@Getter
public class CouponVo extends BaseVo {

    private Long id;
    private String name;
    private String contents;
    private String type;
    private Long amount;
    private Long quantity;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private int flag;

    private Long userId;

    private Long couponUserId;
    private Long couponUserQuantity;


    @Builder
    public CouponVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String name, String contents, String type, Long amount, Long quantity, LocalDateTime startDt, LocalDateTime endDt, int flag1, Long userId, Long couponUserId, Long couponUserQuantity) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.type = type;
        this.amount = amount;
        this.quantity = quantity;
        this.startDt = startDt;
        this.endDt = endDt;
        this.flag = flag1;
        this.userId = userId;
        this.couponUserId = couponUserId;
        this.couponUserQuantity = couponUserQuantity;
    }

}
