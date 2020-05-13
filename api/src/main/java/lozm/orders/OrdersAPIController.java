package lozm.orders;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.orders.DeleteOrdersDto;
import lozm.dto.orders.GetOrdersDto;
import lozm.dto.orders.PostOrdersDto;
import lozm.dto.orders.PutOrdersDto;
import lozm.vo.orders.OrdersVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/orders")
@RestController
@RequiredArgsConstructor
public class OrdersAPIController {

    private final OrdersService ordersService;


    @GetMapping
    public APIResponseDto getOrders() {
        APIResponseDto resDto = new APIResponseDto<>();

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
    public APIResponseDto postOrders(@RequestBody @Valid PostOrdersDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

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
    public APIResponseDto putOrders(@RequestBody @Valid PutOrdersDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

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
    public APIResponseDto deleteOrders(@RequestBody @Valid DeleteOrdersDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

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
