package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.item.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemAPIService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;
    private final String ITEM_URL = adminApiProps.getUrl() + adminApiProps.getItem();


    public GetItemDto.Response getItemList() throws Exception {
        ApiResponseDto<GetItemDto.Response> responseBody = restTemplate.exchange(
                ITEM_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetItemDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getItemList API.");
        return responseBody.getData();
    }

    public GetItemDto getItemDetail(Long itemId) throws Exception {
        ApiResponseDto<GetItemDto> responseBody = restTemplate.exchange(
                ITEM_URL + "/" + itemId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetItemDto>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getItemDetail API.");
        return responseBody.getData();
    }

    public GetClothingDto.Response getClothing(String itemType) throws Exception {
        ApiResponseDto<GetClothingDto.Response> responseBody = restTemplate.exchange(
                ITEM_URL + "/clothing/" + itemType,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetClothingDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getClothing API.");
        return responseBody.getData();
    }

    public ApiResponseDto postItem(PostItemDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                ITEM_URL,
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postItem API.");
        return responseBody;
    }

    public ApiResponseDto putItem(PutItemDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                ITEM_URL,
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putItem API.");
        return responseBody;
    }

    public ApiResponseDto deleteItem(DeleteItemDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                ITEM_URL,
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteItem API.");
        return responseBody;
    }

}
