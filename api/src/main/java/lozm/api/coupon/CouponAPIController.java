package lozm.api.coupon;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.coupon.GetCouponDto;
import lozm.core.dto.coupon.PostCouponDto;
import lozm.core.dto.coupon.PutCouponDto;
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
            List<GetCouponDto.Response> result = couponService.findAllCoupons();
            resDto.setSuccess(true);
            resDto.setData(result);
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
            couponService.save(reqDto);
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
            couponService.update(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
