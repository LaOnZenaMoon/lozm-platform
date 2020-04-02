package lozm.api.coupon;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.coupon.GetCouponDto;
import lozm.core.dto.coupon.PostCouponDto;
import lozm.core.dto.coupon.PutCouponDto;
import lozm.core.dto.user.PostUserCouponDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.coupon.Coupon;
import lozm.domain.entity.coupon.CouponUser;
import lozm.domain.entity.user.User;
import lozm.domain.repository.coupon.CouponRepository;
import lozm.domain.repository.coupon.CouponUserRepository;
import lozm.domain.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponUserRepository couponUserRepository;


    public List<GetCouponDto.Response> findAllCoupons() {
        List<Coupon> couponList = couponRepository.findAll();

        return couponList.stream().map(c -> new GetCouponDto.Response(
                c.getId(),
                c.getName(),
                c.getContents(),
                c.getType(),
                c.getAmount()
        )).collect(toList());
    }

    @Transactional
    public void save(PostCouponDto.Request reqDto) throws Exception {
        Coupon coupon = new Coupon();
        coupon.insertCoupon(reqDto);

        couponRepository.save(coupon);
    }

    @Transactional
    public void update(PutCouponDto.Request reqDto) throws Exception {
        Optional<Coupon> findCoupon = couponRepository.findById(reqDto.getId());
        findCoupon.orElseThrow(() -> new APIException("COUPON_0002", "Coupon doesn't exist."));
        findCoupon.get().updateCoupon(reqDto);
    }

    @Transactional
    public void saveCouponUser(PostUserCouponDto.Request reqDto) throws Exception {
        Optional<User> findUser = userRepository.findById(reqDto.getUserId());
        findUser.orElseThrow(() -> new APIException("USER_0002", "User doesn't exist."));

        Optional<Coupon> findCoupon = couponRepository.findById(reqDto.getCouponId());
        findCoupon.orElseThrow(() -> new APIException("USER_SAVE_NO_COUPON", "Coupon doesn't exist."));

        CouponUser couponUser = new CouponUser();
        couponUser.insertCouponUser(reqDto.getCouponQuantity(), findCoupon.get(), findUser.get());

        couponUserRepository.save(couponUser);

        findCoupon.get().decreaseCouponQuantity(reqDto.getCouponQuantity());
    }
    
}
