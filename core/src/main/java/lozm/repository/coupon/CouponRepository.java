package lozm.repository.coupon;

import lozm.entity.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT C FROM Coupon C WHERE C.flag = 1")
    List<Coupon> selectCouponList();

}