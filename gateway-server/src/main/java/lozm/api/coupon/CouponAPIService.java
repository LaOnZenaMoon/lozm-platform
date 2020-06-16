package lozm.api.coupon;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.coupon.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponAPIService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;
    private final String COUPON_URL = adminApiProps.getUrl() + adminApiProps.getCoupon();


    public GetCouponDto.Response getCoupon() throws Exception {
        ApiResponseDto<GetCouponDto.Response> responseBody = restTemplate.exchange(
                COUPON_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetCouponDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getCoupon API.");
        return responseBody.getData();
    }

    public GetCouponDto getCouponDetail(Long couponId) throws Exception {
        ApiResponseDto<GetCouponDto> responseBody = restTemplate.exchange(
                COUPON_URL + "/" + couponId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetCouponDto>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getCouponDetail API.");
        return responseBody.getData();
    }

    public ApiResponseDto postCoupon(PostCouponDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                COUPON_URL,
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postCoupon API.");
        return responseBody;
    }

    public ApiResponseDto putCoupon(PutCouponDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                COUPON_URL,
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putCoupon API.");
        return responseBody;
    }

    public ApiResponseDto deleteCoupon(DeleteCouponDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                COUPON_URL,
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteCoupon API.");
        return responseBody;
    }

    public GetCouponUserDto.Response getCouponUser(Long couponId) throws Exception {
        ApiResponseDto<GetCouponUserDto.Response> responseBody = restTemplate.exchange(
                COUPON_URL + "/" + couponId + "/user",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetCouponUserDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getCouponUser API.");
        return responseBody.getData();
    }

    public ApiResponseDto postCouponUser(PostCouponUserDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                COUPON_URL + "/user",
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postCouponUser API.");
        return responseBody;
    }

    public ApiResponseDto putCouponUser(PutCouponUserDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                COUPON_URL + "/user",
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putCouponUser API.");
        return responseBody;
    }

    public ApiResponseDto deleteCouponUser(DeleteCouponUserDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                COUPON_URL + "/user",
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteCouponUser API.");
        return responseBody;
    }

}