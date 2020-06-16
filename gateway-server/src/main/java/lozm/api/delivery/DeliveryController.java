package lozm.api.delivery;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.delivery.DeleteDeliveryDto;
import lozm.object.dto.delivery.PostDeliveryDto;
import lozm.object.dto.delivery.PutDeliveryDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/delivery")
@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryAPIService deliveryAPIService;


    @GetMapping
    public ApiResponseDto getDelivery() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, deliveryAPIService.getDelivery());
    }

    @PostMapping
    public ApiResponseDto postDelivery(@RequestBody @Valid PostDeliveryDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, deliveryAPIService.postDelivery(reqDto));
    }

    @PutMapping
    public ApiResponseDto putDelivery(@RequestBody @Valid PutDeliveryDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, deliveryAPIService.putDelivery(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteDelivery(@RequestBody @Valid DeleteDeliveryDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, deliveryAPIService.deleteDelivery(reqDto));
    }

}
