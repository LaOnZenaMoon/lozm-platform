package lozm.api.store;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.item.GetClothingDto;
import lozm.object.dto.store.DeleteStoreDto;
import lozm.object.dto.store.GetStoreDto;
import lozm.object.dto.store.PostStoreDto;
import lozm.object.dto.store.PutStoreDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;
    private final String STORE_URL = adminApiProps.getUrl() + adminApiProps.getStore();

    public GetStoreDto.Response getStore() {
        ApiResponseDto<GetStoreDto.Response> responseBody = restTemplate.exchange(
                STORE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetStoreDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStore API.");
        return responseBody.getData();
    }

    public GetStoreDto getStoreDetail(Long storeId) {
        ApiResponseDto<GetStoreDto> responseBody = restTemplate.exchange(
                STORE_URL + "/" + storeId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetStoreDto>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStoreDetail API.");
        return responseBody.getData();
    }

    public GetClothingDto.Response getStoreClothing(Long storeId, String itemType) {
        ApiResponseDto<GetClothingDto.Response> responseBody = restTemplate.exchange(
                STORE_URL + "/" + storeId + "/clothing/" + itemType,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetClothingDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStoreClothing API.");
        return responseBody.getData();
    }

    public ApiResponseDto postStore(PostStoreDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                STORE_URL,
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStoreClothing API.");
        return responseBody;
    }

    public ApiResponseDto putStore(PutStoreDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                STORE_URL,
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStoreClothing API.");
        return responseBody;
    }

    public ApiResponseDto deleteStore(DeleteStoreDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                STORE_URL,
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStoreClothing API.");
        return responseBody;
    }

}
