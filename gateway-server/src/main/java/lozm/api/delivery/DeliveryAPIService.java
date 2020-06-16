package lozm.api.delivery;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.delivery.DeleteDeliveryDto;
import lozm.object.dto.delivery.GetDeliveryDto;
import lozm.object.dto.delivery.PostDeliveryDto;
import lozm.object.dto.delivery.PutDeliveryDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryAPIService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;
    private final String DELIVERY_URL = adminApiProps.getUrl() + adminApiProps.getDelivery();


    public GetDeliveryDto.Response getDelivery() {
        ApiResponseDto<GetDeliveryDto.Response> responseBody = restTemplate.exchange(
                DELIVERY_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetDeliveryDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getDelivery API.");
        return responseBody.getData();
    }

    public ApiResponseDto postDelivery(PostDeliveryDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                DELIVERY_URL,
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postDelivery API.");
        return responseBody;
    }

    public ApiResponseDto putDelivery(PutDeliveryDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                DELIVERY_URL,
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putDelivery API.");
        return responseBody;
    }

    public ApiResponseDto deleteDelivery(DeleteDeliveryDto.Request reqDto) {
        ApiResponseDto responseBody = restTemplate.exchange(
                DELIVERY_URL,
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteDelivery API.");
        return responseBody;
    }
    
}
