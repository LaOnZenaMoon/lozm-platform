package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.global.properties.AdminApiProps;
import lozm.global.service.BaseService;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.orders.DeleteOrdersDto;
import lozm.object.dto.orders.GetOrdersDto;
import lozm.object.dto.orders.PostOrdersDto;
import lozm.object.dto.orders.PutOrdersDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersAPIService {

    private final RestTemplate restTemplate;
    private final AdminApiProps adminApiProps;


    public GetOrdersDto.Response getOrders() throws Exception {
        ApiResponseDto<GetOrdersDto.Response> responseBody = restTemplate.exchange(
                adminApiProps.getOrdersUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponseDto<GetOrdersDto.Response>>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface getOrders API.");
        return responseBody.getData();
    }

    public ApiResponseDto postOrders(PostOrdersDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getOrdersUrl(),
                HttpMethod.POST,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface postOrders API.");
        return responseBody;
    }

    public ApiResponseDto putOrders(PutOrdersDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getOrdersUrl(),
                HttpMethod.PUT,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface putOrders API.");
        return responseBody;
    }

    public ApiResponseDto deleteOrders(DeleteOrdersDto.Request reqDto) throws Exception {
        ApiResponseDto responseBody = restTemplate.exchange(
                adminApiProps.getOrdersUrl(),
                HttpMethod.DELETE,
                new HttpEntity<>(reqDto),
                new ParameterizedTypeReference<ApiResponseDto>() {}
        ).getBody();

        BaseService.checkResponseBody(responseBody, "Failed to interface deleteOrders API.");
        return responseBody;
    }
}
