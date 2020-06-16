package lozm.api.orders;

import lombok.RequiredArgsConstructor;
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
    public ApiResponseDto getOrders() {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            List<GetOrdersDto> result = ordersService.getOrdersList();
            GetOrdersDto.Response ordersResDto = new GetOrdersDto.Response();
            ordersResDto.setList(result);

            resDto.setSuccess(true);
            resDto.setData(ordersResDto);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public ApiResponseDto postOrders(@RequestBody @Valid PostOrdersDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            OrdersVo ordersVo = OrdersVo.builder()
                    .quantity(reqDto.getQuantity())
                    .userId(reqDto.getUserId())
                    .itemId(reqDto.getItemId())
                    .deliveryId(reqDto.getDeliveryId())
                    .couponId(reqDto.getCouponId())
                    .build();

            ordersService.save(ordersVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public ApiResponseDto putOrders(@RequestBody @Valid PutOrdersDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            OrdersVo ordersVo = OrdersVo.builder()
                    .id(reqDto.getId())
                    .status(reqDto.getStatus())
                    .flag(1)
                    .build();

            ordersService.update(ordersVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @DeleteMapping
    public ApiResponseDto deleteOrders(@RequestBody @Valid DeleteOrdersDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            for (DeleteOrdersDto dto : reqDto.getList()) {
                OrdersVo ordersVo = OrdersVo.builder()
                        .id(dto.getOrdersId())
                        .flag(0)
                        .build();

                ordersService.delete(ordersVo);
            }

            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
