package lozm.api.delivery;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;

import lozm.core.dto.delivery.GetDeliveryDto;
import lozm.core.dto.delivery.PostDeliveryDto;
import lozm.core.dto.delivery.PutDeliveryDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/delivery")
@RestController
@RequiredArgsConstructor
public class DeliveryAPIController {

    private final DeliveryService deliveryService;


    @GetMapping
    public APIResponseDto getDelivery() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetDeliveryDto.Response> result = deliveryService.findAllDeliveries();
            resDto.setSuccess(true);
            resDto.setData(result);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postDelivery(@RequestBody @Valid PostDeliveryDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            deliveryService.save(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public APIResponseDto putDelivery(@RequestBody @Valid PutDeliveryDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            deliveryService.update(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
