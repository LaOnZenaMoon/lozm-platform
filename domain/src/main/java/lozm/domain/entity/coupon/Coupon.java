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

        if (CouponType.RATIO.equals(this.type)) {

        } else if(CouponType.PRICE.equals(this.type)) {

        } else {
            new APIException("ORDERS_SAVE_NO_COUPON_TYPE", "Coupon type doesn't exist.");
        }

        return rtnVal;
    }
}
