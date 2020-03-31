package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.orders.GetOrdersDto;
import lozm.core.dto.orders.PostOrdersDto;
import lozm.core.dto.orders.PutOrdersDto;
import lozm.core.dto.user.PostUserDto;
import lozm.core.dto.user.PutUserDto;
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
            List<GetOrdersDto.Response> result = ordersService.findAllOrders();
            resDto.setSuccess(true);
            resDto.setData(result);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postOrders(@RequestBody @Valid PostOrdersDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            ordersService.save(reqDto);
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
            ordersService.update(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
