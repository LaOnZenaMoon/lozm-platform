package lozm.api.route;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.store.GetStoreDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;


    public GetStoreDto.Response getStoreDetail(Long storeId) throws Exception {
        String url = adminApiProps.getUrl() + adminApiProps.getStore() + "/" + storeId;
        ApiResponseDto<GetStoreDto.Response> responseBody = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetStoreDto.Response>>() {
                }
        ).getBody();

        BaseService.checkResponseBody(responseBody, "");
        return responseBody.getData();
    }

    public ApiResponseDto getStoreClothing(Long storeId, String outer) {

    }

    public ApiResponseDto getCouponDetail(Long couponId) {

    }

    public ApiResponseDto getCouponUser(Long couponId) {

    }
}
