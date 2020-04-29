package lozm.coupon;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.coupon.*;
import lozm.vo.coupon.CouponVo;
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
            List<GetCouponDto> result = couponService.getCouponList();
            GetCouponDto.Response couponResDto = new GetCouponDto.Response();
            couponResDto.setList(result);

            resDto.setData(couponResDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @GetMapping("/{couponId}")
    public APIResponseDto getCouponDetail(@PathVariable(value = "couponId") Long couponId) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            CouponVo couponVo = CouponVo.builder()
                    .id(couponId)
                    .build();

            GetCouponDto result = couponService.getCouponDetail(couponVo);

            resDto.setData(result);
            resDto.setSuccess(true);
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
                    .flag(1)
                    .build();

            couponService.update(couponVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @DeleteMapping
    public APIResponseDto deleteCoupon(@RequestBody @Valid DeleteCouponDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            for(DeleteCouponDto dto : reqDto.getList()) {
                CouponVo couponVo = CouponVo.builder()
                        .id(dto.getId())
                        .flag(0)
                        .build();

                couponService.delete(couponVo);
            }

            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @GetMapping(value = "/{couponId}/user")
    public APIResponseDto getCouponUser(@PathVariable(value = "couponId") Long couponId) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            CouponVo couponVo = CouponVo.builder()
                    .id(couponId)
                    .build();

            List<GetCouponUserDto> couponUserList = couponService.getCouponUserList(couponVo);
            GetCouponUserDto.Response couponUserResDto = new GetCouponUserDto.Response();
            couponUserResDto.setList(couponUserList);

            resDto.setData(couponUserResDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping(value = "/user")
    public APIResponseDto postCouponUser(@RequestBody @Valid PostCouponUserDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            CouponVo couponVo = CouponVo.builder()
                    .id(reqDto.getCouponId())
                    .userId(reqDto.getUserId())
                    .couponUserQuantity(reqDto.getCouponUserQuantity())
                    .build();

            couponService.saveCouponUser(couponVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
