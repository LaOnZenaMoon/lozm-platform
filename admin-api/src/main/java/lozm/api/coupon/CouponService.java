package lozm.api.coupon;

import lombok.RequiredArgsConstructor;
import lozm.global.exception.ServiceException;
import lozm.object.dto.coupon.GetCouponDto;
import lozm.object.dto.coupon.GetCouponUserDto;
import lozm.object.vo.coupon.CouponVo;
import lozm.entity.coupon.Coupon;
import lozm.entity.coupon.CouponUser;
import lozm.entity.auth.Account;
import lozm.repository.coupon.CouponRepository;
import lozm.repository.coupon.CouponUserRepository;
import lozm.repository.auth.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final AccountRepository accountRepository;
    private final CouponUserRepository couponUserRepository;


    public List<GetCouponDto> getCouponList() {
        List<Coupon> couponList = couponRepository.selectCouponList();
        List<GetCouponDto> rtnList = new ArrayList<>();
        for (Coupon coupon : couponList) {
            GetCouponDto dto = GetCouponDto.builder()
                    .id(coupon.getId())
                    .name(coupon.getName())
                    .contents(coupon.getContents())
                    .type(String.valueOf(coupon.getType()))
                    .amount(coupon.getAmount())
                    .quantity(coupon.getQuantity())
                    .startDt(coupon.getStartDt())
                    .endDt(coupon.getEndDt())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    public GetCouponDto getCouponDetail(CouponVo couponVo) {
        Optional<Coupon> findCoupon = findCoupon(couponVo.getId(), "COUPON_0002");
        Coupon coupon = findCoupon.get();
        return GetCouponDto.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .contents(coupon.getContents())
                .type(String.valueOf(coupon.getType()))
                .amount(coupon.getAmount())
                .quantity(coupon.getQuantity())
                .startDt(coupon.getStartDt())
                .endDt(coupon.getEndDt())
                .build();
    }

    @Transactional
    public void save(CouponVo couponVo) throws Exception {
        Coupon coupon = new Coupon();
        coupon.insertCoupon(couponVo);

        couponRepository.save(coupon);
    }

    @Transactional
    public void update(CouponVo couponVo) throws Exception {
        Optional<Coupon> findCoupon = findCoupon(couponVo.getId(), "COUPON_0002");
        findCoupon.get().updateCoupon(couponVo);
    }

    @Transactional
    public void delete(CouponVo couponVo) throws Exception {
        Optional<Coupon> findCoupon = findCoupon(couponVo.getId(), "COUPON_0002");
        findCoupon.get().deleteCoupon(couponVo);
    }

    public List<GetCouponUserDto> getCouponUserList(CouponVo couponVo) {
        List<CouponUser> couponUserList = couponUserRepository.selectCouponUserList(couponVo.getId());
        List<GetCouponUserDto> rtnList = new ArrayList<>();
        for (CouponUser couponUser : couponUserList) {
            GetCouponUserDto dto = GetCouponUserDto.builder()
                    .id(couponUser.getId())
                    .quantity(couponUser.getQuantity())
                    .userId(couponUser.getAccount().getId())
                    .couponId(couponUser.getCoupon().getId())
                    .userName(couponUser.getAccount().getName())
                    .userIdentifier(couponUser.getAccount().getIdentifier())
                    .userType(couponUser.getAccount().getType())
                    .build();
            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void postCouponUser(CouponVo couponVo) throws Exception {
        //Find and check the coupon
        Optional<Coupon> findCoupon = findCoupon(couponVo.getId(), "USER_SAVE_NO_COUPON");

        //Find the user
        Optional<Account> findUser = accountRepository.findById(couponVo.getUserId());
        findUser.orElseThrow(() -> {
            throw new ServiceException("USER_0002", "User doesn't exist.");
        });

        //Find the coupon user
        List<CouponUser> findCouponUserList = couponUserRepository.selectCouponUserByUserIdAndCouponId(couponVo.getUserId(), couponVo.getId());
        if(findCouponUserList.size() > 0) {
            for (CouponUser couponUser : findCouponUserList) {
                Long couponUserQuantity = couponUser.getQuantity();
                if(couponUserQuantity > couponVo.getCouponUserQuantity()) {
                    findCoupon.get().increaseCouponQuantity(couponUserQuantity - couponVo.getCouponUserQuantity());
                } else if(couponUserQuantity < couponVo.getCouponUserQuantity()) {
                    findCoupon.get().decreaseCouponQuantity(couponVo.getCouponUserQuantity() - couponUserQuantity);
                }

                couponUser.updateCouponUser(couponVo);
            }

            return;
        }

        CouponUser couponUser = new CouponUser();
        couponUser.insertCouponUser(couponVo.getCouponUserQuantity(), findCoupon.get(), findUser.get());
        couponUserRepository.save(couponUser);

        findCoupon.get().decreaseCouponQuantity(couponVo.getCouponUserQuantity());
    }

    @Transactional
    public void putCouponUser(CouponVo couponVo) throws Exception {
        Optional<CouponUser> findCouponUser = findCouponUser(couponVo.getId());
        calculateCouponQuantity(couponVo, findCouponUser.get().getCoupon(), findCouponUser.get());
        findCouponUser.get().updateCouponUser(couponVo);
    }

    @Transactional
    public void deleteCouponUser(CouponVo couponVo) throws Exception {
        Optional<CouponUser> findCouponUser = findCouponUser(couponVo.getId());
        findCouponUser.get().deleteCouponUser(couponVo);

        //Find and check the coupon
        Optional<Coupon> findCoupon = findCoupon(couponVo.getId(), "USER_SAVE_NO_COUPON");
        findCoupon.get().increaseCouponQuantity(findCouponUser.get().getQuantity());
    }

    private void calculateCouponQuantity(CouponVo couponVo, Coupon findCoupon, CouponUser findCouponUser) {
        Long couponUserQuantity = findCouponUser.getQuantity();

        if(couponUserQuantity > couponVo.getCouponUserQuantity()) {
            findCoupon.increaseCouponQuantity(couponUserQuantity - couponVo.getCouponUserQuantity());
        } else if(couponUserQuantity < couponVo.getCouponUserQuantity()) {
            findCoupon.decreaseCouponQuantity(couponVo.getCouponUserQuantity() - couponUserQuantity);
        }
    }

    private Optional<Coupon> findCoupon(Long couponId, String exceptionCode) {
        Optional<Coupon> findCoupon = couponRepository.findById(couponId);
        findCoupon.orElseThrow(() -> {
            throw new ServiceException(exceptionCode, "Coupon doesn't exist.");
        });
        return findCoupon;
    }

    private Optional<CouponUser> findCouponUser(Long couponId) {
        Optional<CouponUser> findCouponUser = couponUserRepository.findById(couponId);
        findCouponUser.orElseThrow(() -> {
            throw new ServiceException("COUPON_USER_0002", "Coupon user doesn't exist.");
        });
        return findCouponUser;
    }

}
