package lozm.domain.entity.coupon;

import lombok.Getter;
import lozm.core.code.CouponType;
import lozm.core.dto.coupon.PostCouponDto;
import lozm.core.dto.coupon.PutCouponDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Table(schema = "LOZM", name = "COUPON")
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "COUPON_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENTS")
    private String contents;

    @Column(name = "TYPE")
    private CouponType type;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "START_DT")
    private LocalDateTime startDt;

    @Column(name = "END_DT")
    private LocalDateTime endDt;


    public void insertCoupon(PostCouponDto.Request reqDto) {
        this.name = reqDto.getName();
        this.contents = reqDto.getContents();
        this.type = CouponType.valueOf(reqDto.getType());
        this.amount = reqDto.getAmount();
        this.quantity = reqDto.getQuantity();
        this.startDt = reqDto.getStartDt();
        this.endDt = reqDto.getEndDt();
    }

    public void updateCoupon(PutCouponDto.Request reqDto) {
        this.name = reqDto.getName();
        this.contents = reqDto.getContents();
        this.type = CouponType.valueOf(reqDto.getType());
        this.amount = reqDto.getAmount();
        this.quantity = reqDto.getQuantity();
        this.startDt = reqDto.getStartDt();
        this.endDt = reqDto.getEndDt();
        this.setBaseEntity("", reqDto.getFlag());
    }

    public Long calculateOrderedPrice(Long orderedPrice) throws Exception {
        Long rtnVal = -1L;

        LocalDateTime sysdateTime = LocalDateTime.now();
        if(sysdateTime.isBefore(this.startDt) || sysdateTime.isAfter(this.endDt)) {
            throw new APIException("ORDERS_SAVE_NOT_COUPON_PERIOD", "It is not the period that coupon is available.");
        }

        if (CouponType.RATIO.equals(this.type)) {
            rtnVal = orderedPrice * ((100 - this.amount) / 100);
        } else if(CouponType.PRICE.equals(this.type)) {
            rtnVal = orderedPrice - this.amount;
        } else {
            throw new APIException("ORDERS_SAVE_NO_COUPON_TYPE", "Coupon type doesn't exist.");
        }

        if(rtnVal < 0) {
            throw new APIException("ORDERS_SAVE_COUPON_CALC_PRICE", "Discounted price can't be lower than 0.");
        }

        return rtnVal;
    }

    public void decreaseCouponQuantity(Long couponQuantity) {
        this.quantity -= couponQuantity;
        if(this.quantity < 0) throw new APIException("USER_SAVE_NO_COUPON_QUANTITY", "Coupon quantity is insufficient.");
    }
}
