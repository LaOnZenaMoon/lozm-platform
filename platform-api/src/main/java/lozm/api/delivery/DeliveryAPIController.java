package lozm.api.delivery;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;

import lozm.object.dto.delivery.DeleteDeliveryDto;
import lozm.object.dto.delivery.GetDeliveryDto;
import lozm.object.dto.delivery.PostDeliveryDto;
import lozm.object.dto.delivery.PutDeliveryDto;
import lozm.object.vo.delivery.DeliveryVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController @RequestMapping(value = "/api/delivery")
@RequiredArgsConstructor
public class DeliveryAPIController {

    private final DeliveryService deliveryService;


    @GetMapping
    public ApiResponseDto getDelivery() {
        List<GetDeliveryDto> result = deliveryService.getDeliveryList();

        GetDeliveryDto.Response resDto = new GetDeliveryDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postDelivery(@RequestBody @Valid PostDeliveryDto.Request reqDto) {
        DeliveryVo deliveryVo = DeliveryVo.builder()
                .country(reqDto.getCountry())
                .zipCode(reqDto.getZipCode())
                .city(reqDto.getCity())
                .street(reqDto.getStreet())
                .etc(reqDto.getEtc())
                .status(reqDto.getStatus())
                .createdBy(reqDto.getCreatedBy())
                .build();

        deliveryService.save(deliveryVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putDelivery(@RequestBody @Valid PutDeliveryDto.Request reqDto) {
        DeliveryVo deliveryVo = DeliveryVo.builder()
                .id(reqDto.getId())
                .country(reqDto.getCountry())
                .zipCode(reqDto.getZipCode())
                .city(reqDto.getCity())
                .street(reqDto.getStreet())
                .etc(reqDto.getEtc())
                .status(reqDto.getStatus())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

        deliveryService.update(deliveryVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteDelivery(@RequestBody @Valid DeleteDeliveryDto.Request reqDto) {
        for (DeleteDeliveryDto dto : reqDto.getList()) {
            DeliveryVo deliveryVo = DeliveryVo.builder()
                    .id(dto.getId())
                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            deliveryService.delete(deliveryVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
