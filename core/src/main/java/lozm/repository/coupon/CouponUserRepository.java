package lozm.repository.coupon;

import lozm.entity.coupon.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponUserRepository extends JpaRepository<CouponUser, Long> {

    @Query("SELECT CU FROM CouponUser CU WHERE CU.flag = 1")
    List<CouponUser> selectCouponUserList();

}