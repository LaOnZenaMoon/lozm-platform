package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.orders.DeleteOrdersDto;
import lozm.object.dto.orders.GetOrdersDto;
import lozm.object.dto.orders.PostOrdersDto;
import lozm.object.dto.orders.PutOrdersDto;
import lozm.object.vo.orders.OrdersVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/orders")
@RestController
@RequiredArgsConstructor
public class OrdersAPIController {

    private final OrdersService ordersService;


    @GetMapping
    public ApiResponseDto getOrders() throws Exception {
        List<GetOrdersDto> result = ordersService.getOrdersList();

        GetOrdersDto.Response resDto = new GetOrdersDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postOrders(@RequestBody @Valid PostOrdersDto.Request reqDto) throws Exception {
        OrdersVo ordersVo = OrdersVo.builder()
                .quantity(reqDto.getQuantity())
                .userId(reqDto.getUserId())
                .itemId(reqDto.getItemId())
                .deliveryId(reqDto.getDeliveryId())
                .couponId(reqDto.getCouponId())
                .build();

        ordersService.save(ordersVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, reqDto);
    }

    @PutMapping
    public ApiResponseDto putOrders(@RequestBody @Valid PutOrdersDto.Request reqDto) throws Exception {
        OrdersVo ordersVo = OrdersVo.builder()
                .id(reqDto.getId())
                .status(reqDto.getStatus())
                .flag(1)
                .build();

        ordersService.update(ordersVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteOrders(@RequestBody @Valid DeleteOrdersDto.Request reqDto) throws Exception {
        for (DeleteOrdersDto dto : reqDto.getList()) {
            OrdersVo ordersVo = OrdersVo.builder()
                    .id(dto.getOrdersId())
                    .flag(0)
                    .build();

            ordersService.delete(ordersVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
