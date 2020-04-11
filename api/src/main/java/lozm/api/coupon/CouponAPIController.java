package lozm.api.coupon;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.coupon.GetCouponDto;
import lozm.core.dto.coupon.PostCouponDto;
import lozm.core.dto.coupon.PutCouponDto;
import lozm.core.dto.user.PostUserCouponDto;
import lozm.core.vo.coupon.CouponVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/coupon")
@RestController
@RequiredArgsConstructor
public class CouponAPIController {

    private final CouponService couponService;


    @GetMapping
    public APIResponseDto getCoupon() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetCouponDto> result = couponService.findAllCoupons();
            GetCouponDto.Response couponResDto = new GetCouponDto.Response();
            couponResDto.setList(result);

            resDto.setSuccess(true);
            resDto.setData(couponResDto);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postCoupon(@RequestBody @Valid PostCouponDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            CouponVo couponVo = CouponVo.builder()
                    .name(reqDto.getName())
                    .contents(reqDto.getContents())
                    .type(reqDto.getType())
                    .amount(reqDto.getAmount())
                    .quantity(reqDto.getQuantity())
                    .startDt(reqDto.getStartDt())
                    .endDt(reqDto.getEndDt())
                    .build();

            couponService.save(couponVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public APIResponseDto putCoupon(@RequestBody @Valid PutCouponDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            CouponVo couponVo = CouponVo.builder()
                    .id(reqDto.getId())
                    .name(reqDto.getName())
                    .contents(reqDto.getContents())
                    .type(reqDto.getType())
                    .amount(reqDto.getAmount())
                    .quantity(reqDto.getQuantity())
                    .startDt(reqDto.getStartDt())
                    .endDt(reqDto.getEndDt())
                    .flag(reqDto.getFlag())
                    .build();

            couponService.update(couponVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping(value = "/user")
    public APIResponseDto postCouponUser(@RequestBody @Valid PostUserCouponDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            couponService.saveCouponUser(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
