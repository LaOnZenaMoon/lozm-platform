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

    @Column(name = "TYPE")
    private CouponType type;

    @Column(name = "CONTENTS")
    private Long contents;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "START_DT")
    private LocalDateTime startDt;

    @Column(name = "END_DT")
    private LocalDateTime endDt;

}
