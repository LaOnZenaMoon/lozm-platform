package lozm.api.coupon;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.coupon.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/coupon")
@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponAPIService couponAPIService;


    @GetMapping
    public ApiResponseDto getCoupon() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.getCoupon());
    }

    @GetMapping("/{couponId}")
    public ApiResponseDto getCouponDetail(@PathVariable(value = "couponId") Long couponId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.getCouponDetail(couponId));
    }

    @PostMapping
    public ApiResponseDto postCoupon(@RequestBody @Valid PostCouponDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.postCoupon(reqDto));
    }

    @PutMapping
    public ApiResponseDto putCoupon(@RequestBody @Valid PutCouponDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.putCoupon(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteCoupon(@RequestBody @Valid DeleteCouponDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.deleteCoupon(reqDto));
    }

    @GetMapping(value = "/{couponId}/user")
    public ApiResponseDto getCouponUser(@PathVariable(value = "couponId") Long couponId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.getCouponUser(couponId));
    }

    @PostMapping(value = "/user")
    public ApiResponseDto postCouponUser(@RequestBody @Valid PostCouponUserDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.postCouponUser(reqDto));
    }

    @PutMapping(value = "/user")
    public ApiResponseDto putCouponUser(@RequestBody @Valid PutCouponUserDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.putCouponUser(reqDto));
    }

    @DeleteMapping(value = "/user")
    public ApiResponseDto deleteCouponUser(@RequestBody @Valid DeleteCouponUserDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, couponAPIService.deleteCouponUser(reqDto));
    }

}
