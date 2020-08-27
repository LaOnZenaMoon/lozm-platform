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
public class StoreAPIService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;


    public GetStoreDto.Response getStore() throws Exception {
        ApiResponseDto<GetStoreDto.Response> responseBody = restTemplate.exchange(
                adminApiProps.getStoreUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetStoreDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStore API.");
        return responseBody.getData();
    }

    public GetStoreDto getStoreDetail(Long storeId) throws Exception {
        ApiResponseDto<GetStoreDto> responseBody = restTemplate.exchange(
                adminApiProps.getStoreUrl() + "/" + storeId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetStoreDto>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStoreDetail API.");
        return responseBody.getData();
    }

    public GetClothingDto.Response getStoreClothing(Long storeId, String itemType) throws Exception {
        ApiResponseDto<GetClothingDto.Response> responseBody = restTemplate.exchange(
                adminApiProps.getStoreUrl() + "/" + storeId + "/clothing/" + itemType,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetClothingDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getStoreClothing API.");
        return responseBody.getData();
    }

    public ApiResponseDto postStore(PostStoreDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getStoreUrl(),
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postStore API.");
        return responseBody;
    }

    public ApiResponseDto putStore(PutStoreDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getStoreUrl(),
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putStore API.");
        return responseBody;
    }

    public ApiResponseDto deleteStore(DeleteStoreDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getStoreUrl(),
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteStore API.");
        return responseBody;
    }

}
