package lozm.coupon;

import lombok.RequiredArgsConstructor;
import lozm.dto.coupon.GetCouponDto;
import lozm.dto.coupon.GetCouponUserDto;
import lozm.exception.APIException;
import lozm.vo.coupon.CouponVo;
import lozm.entity.coupon.Coupon;
import lozm.entity.coupon.CouponUser;
import lozm.entity.user.User;
import lozm.repository.coupon.CouponRepository;
import lozm.repository.coupon.CouponUserRepository;
import lozm.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    public List<GetCouponDto> getCouponList() {
        List<Coupon> couponList = couponRepository.selectCouponList();

        return couponList.stream().map(c -> new GetCouponDto(
                c.getId(),
                c.getName(),
                c.getContents(),
                c.getType().name(),
                c.getAmount(),
                c.getQuantity(),
                c.getStartDt(),
                c.getEndDt()
        )).collect(toList());
    }

    public GetCouponDto getCouponDetail(CouponVo couponVo) {
        Optional<Coupon> findCoupon = couponRepository.findById(couponVo.getId());
        findCoupon.orElseThrow(() -> {
            throw new APIException("COUPON_0002", "Coupon doesn't exist.");
        });

        Coupon coupon = findCoupon.get();
        return new GetCouponDto(
                coupon.getId(),
                coupon.getName(),
                coupon.getContents(),
                coupon.getType().name(),
                coupon.getAmount(),
                coupon.getQuantity(),
                coupon.getStartDt(),
                coupon.getEndDt()
        );
    }

    @Transactional
    public void save(CouponVo couponVo) throws Exception {
        Coupon coupon = new Coupon();
        coupon.insertCoupon(couponVo);

        couponRepository.save(coupon);
    }

    @Transactional
    public void update(CouponVo couponVo) throws Exception {
        Optional<Coupon> findCoupon = couponRepository.findById(couponVo.getId());
        findCoupon.orElseThrow(() -> {
            throw new APIException("COUPON_0002", "Coupon doesn't exist.");
        });

        findCoupon.get().updateCoupon(couponVo);
    }

    @Transactional
    public void delete(CouponVo couponVo) throws Exception {
        Optional<Coupon> findCoupon = couponRepository.findById(couponVo.getId());
        findCoupon.orElseThrow(() -> {
            throw new APIException("COUPON_0002", "Coupon doesn't exist.");
        });

        findCoupon.get().deleteCoupon(couponVo);
    }

    public List<GetCouponUserDto> getCouponUserList(CouponVo couponVo) {
        List<CouponUser> couponUserList = couponUserRepository.selectCouponUserList();
        List<GetCouponUserDto> rtnList = new ArrayList<>();
        for (CouponUser couponUser : couponUserList) {
            GetCouponUserDto dto = new GetCouponUserDto(
                    couponUser.getId(),
                    couponUser.getQuantity(),
                    couponUser.getUser().getId(),
                    couponUser.getUser().getName(),
                    couponUser.getUser().getIdentifier(),
                    couponUser.getUser().getType()
            );
            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void postCouponUser(CouponVo couponVo) throws Exception {
        //Find and check the coupon
        Optional<Coupon> findCoupon = couponRepository.findById(couponVo.getId());
        findCoupon.orElseThrow(() -> {
            throw new APIException("USER_SAVE_NO_COUPON", "Coupon doesn't exist.");
        });

        findCoupon.get().decreaseCouponQuantity(couponVo.getCouponUserQuantity());

        couponRepository.save(findCoupon.get());

        //Find the user
        Optional<User> findUser = userRepository.findById(couponVo.getUserId());
        findUser.orElseThrow(() -> {
            throw new APIException("USER_0002", "User doesn't exist.");
        });

        //Find the coupon user
        List<CouponUser> findCouponUserList = couponUserRepository.selectCouponUserByUserIdAndCouponId(couponVo.getUserId(), couponVo.getId());
        if(findCouponUserList.size() > 0) {
            for (CouponUser couponUser : findCouponUserList) {
                couponUser.updateCouponUser(couponVo);
                couponUserRepository.save(couponUser);
            }

            return;
        }

        CouponUser couponUser = new CouponUser();
        couponUser.insertCouponUser(couponVo.getCouponUserQuantity(), findCoupon.get(), findUser.get());

        couponUserRepository.save(couponUser);
    }


    public void putCouponUser(CouponVo couponVo) throws Exception {
        //Find and check the coupon
        Optional<Coupon> findCoupon = couponRepository.findById(couponVo.getId());
        findCoupon.orElseThrow(() -> {
            throw new APIException("USER_SAVE_NO_COUPON", "Coupon doesn't exist.");
        });

        findCoupon.get().decreaseCouponQuantity(couponVo.getCouponUserQuantity());

        couponRepository.save(findCoupon.get());

        //Find the user
        Optional<User> findUser = userRepository.findById(couponVo.getUserId());
        findUser.orElseThrow(() -> {
            throw new APIException("USER_0002", "User doesn't exist.");
        });

        //Find the coupon user
        List<CouponUser> findCouponUserList = couponUserRepository.selectCouponUserByUserIdAndCouponId(couponVo.getUserId(), couponVo.getId());
        if(findCouponUserList.size() > 0) {
            for (CouponUser couponUser : findCouponUserList) {
                couponUser.updateCouponUser(couponVo);
                couponUserRepository.save(couponUser);
            }

            return;
        } else {
            throw new APIException("COUPON_USER_0002", "Coupon user doesn't exist.");
        }
    }

    public void deleteCouponUser(CouponVo couponVo) throws Exception {
        //Find and check the coupon
        Optional<Coupon> findCoupon = couponRepository.findById(couponVo.getId());
        findCoupon.orElseThrow(() -> {
            throw new APIException("USER_SAVE_NO_COUPON", "Coupon doesn't exist.");
        });

        //Find the user
        Optional<User> findUser = userRepository.findById(couponVo.getUserId());
        findUser.orElseThrow(() -> {
            throw new APIException("USER_0002", "User doesn't exist.");
        });

        List<CouponUser> findCouponUserList = couponUserRepository.selectCouponUserByUserIdAndCouponId(couponVo.getUserId(), couponVo.getId());
        if(findCouponUserList.size() > 0) {
            for (CouponUser couponUser : findCouponUserList) {
                couponUser.deleteCouponUser(couponVo);
                couponUserRepository.save(couponUser);
            }

            return;
        } else {
            throw new APIException("COUPON_USER_0002", "Coupon user doesn't exist.");
        }
    }
}
