package lozm.repository.coupon;

import lozm.entity.coupon.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponUserRepository extends JpaRepository<CouponUser, Long> {



}