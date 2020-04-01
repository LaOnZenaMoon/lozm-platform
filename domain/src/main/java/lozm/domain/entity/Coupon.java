package lozm.domain.entity;

import lombok.Getter;
import lozm.core.code.CouponType;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
