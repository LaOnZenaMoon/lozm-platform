package lozm.entity.coupon;

import lombok.Getter;
import lozm.entity.BaseEntity;
import lozm.entity.user.User;
import lozm.object.vo.coupon.CouponVo;

import javax.persistence.*;

@Entity
@Table(schema = "LOZM", name = "COUPON_USER")
@Getter
public class CouponUser extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "COUPON_USER_ID")
    private Long id;

    @Column(name = "QUANTITY")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPON_ID")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void insertCouponUser(Long couponQuantity, Coupon coupon, User user) {
        this.quantity = couponQuantity;
        this.coupon = coupon;
        this.user = user;
        this.setBaseEntity(user.getId(), null, 1);
    }

    public void updateCouponUser(CouponVo couponVo) {
        this.quantity = couponVo.getCouponUserQuantity();
        this.setBaseEntity(null, couponVo.getModifiedBy(), couponVo.getFlag());
    }

    public void deleteCouponUser(CouponVo couponVo) {
        this.setBaseEntity(null, couponVo.getModifiedBy(), 0);
    }
}
