package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.global.jwt.SignInfo;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.orders.DeleteOrdersDto;
import lozm.object.dto.orders.PostOrdersDto;
import lozm.object.dto.orders.PutOrdersDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersAPIService ordersAPIService;
    private final SignInfo signInfo;


    @GetMapping
    public ApiResponseDto getOrders() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, ordersAPIService.getOrders());
    }

    @PostMapping
    public ApiResponseDto postOrders(@RequestBody @Valid PostOrdersDto.Request reqDto) throws Exception {
        reqDto.setCreatedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, ordersAPIService.postOrders(reqDto));
    }

    @PutMapping
    public ApiResponseDto putOrders(@RequestBody @Valid PutOrdersDto.Request reqDto) throws Exception {
        reqDto.setModifiedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, ordersAPIService.putOrders(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteOrders(@RequestBody @Valid DeleteOrdersDto.Request reqDto) throws Exception {
        reqDto.setModifiedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, ordersAPIService.deleteOrders(reqDto));
    }

}
