package lozm.api.coupon;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.coupon.*;
import lozm.object.dto.user.GetUserDto;
import lozm.object.vo.coupon.CouponVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/coupon")
@RestController
@RequiredArgsConstructor
public class CouponAPIController {

    private final CouponService couponService;


    @GetMapping
    public ApiResponseDto getCoupon() throws Exception {
        List<GetCouponDto> result = couponService.getCouponList();

        GetCouponDto.Response resDto = new GetCouponDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping("/{couponId}")
    public ApiResponseDto getCouponDetail(@PathVariable(value = "couponId") Long couponId) throws Exception {
        CouponVo couponVo = CouponVo.builder()
                .id(couponId)
                .build();

        GetCouponDto resDto = couponService.getCouponDetail(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postCoupon(@RequestBody @Valid PostCouponDto.Request reqDto) throws Exception {
        CouponVo couponVo = CouponVo.builder()
                .name(reqDto.getName())
                .contents(reqDto.getContents())
                .type(reqDto.getType())
                .amount(reqDto.getAmount())
                .quantity(reqDto.getQuantity())
                .startDt(reqDto.getStartDt())
                .endDt(reqDto.getEndDt())
                .createdBy(reqDto.getCreatedBy())
                .build();

        couponService.save(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putCoupon(@RequestBody @Valid PutCouponDto.Request reqDto) throws Exception {
        CouponVo couponVo = CouponVo.builder()
                .id(reqDto.getId())
                .name(reqDto.getName())
                .contents(reqDto.getContents())
                .type(reqDto.getType())
                .amount(reqDto.getAmount())
                .quantity(reqDto.getQuantity())
                .startDt(reqDto.getStartDt())
                .endDt(reqDto.getEndDt())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

        couponService.update(couponVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteCoupon(@RequestBody @Valid DeleteCouponDto.Request reqDto) throws Exception {
        for(DeleteCouponDto dto : reqDto.getList()) {
            CouponVo couponVo = CouponVo.builder()
                    .id(dto.getId())
                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            couponService.delete(couponVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @GetMapping(value = "/{couponId}/user")
    public ApiResponseDto getCouponUser(@PathVariable(value = "couponId") Long couponId) throws Exception {
        CouponVo couponVo = CouponVo.builder()
                .id(couponId)
                .build();

        List<GetCouponUserDto> couponUserList = couponService.getCouponUserList(couponVo);

        GetCouponUserDto.Response resDto = new GetCouponUserDto.Response();
        resDto.setList(couponUserList);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping(value = "/user")
    public ApiResponseDto postCouponUser(@RequestBody @Valid PostCouponUserDto.Request reqDto) throws Exception {
        for (GetUserDto dto : reqDto.getUserList()) {
            CouponVo couponVo = CouponVo.builder()
                    .id(reqDto.getCouponId())
                    .userId(dto.getId())
                    .couponUserQuantity(reqDto.getCouponUserQuantity())
                    .createdBy(reqDto.getCreatedBy())
                    .build();

            couponService.postCouponUser(couponVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping(value = "/user")
    public ApiResponseDto putCouponUser(@RequestBody @Valid PutCouponUserDto.Request reqDto) throws Exception {
        for (GetCouponUserDto dto : reqDto.getList()) {
            CouponVo couponVo = CouponVo.builder()
                    .couponUserId(dto.getId())
                    .userId(dto.getUserId())
                    .couponUserQuantity(reqDto.getCouponUserQuantity())
                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            couponService.putCouponUser(couponVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping(value = "/user")
    public ApiResponseDto deleteCouponUser(@RequestBody @Valid DeleteCouponUserDto.Request reqDto) throws Exception {
        for (GetCouponUserDto dto : reqDto.getList()) {
            CouponVo couponVo = CouponVo.builder()
                    .couponUserId(dto.getId())
                    .userId(dto.getUserId())
                    .id(dto.getCouponId())
                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            couponService.deleteCouponUser(couponVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
