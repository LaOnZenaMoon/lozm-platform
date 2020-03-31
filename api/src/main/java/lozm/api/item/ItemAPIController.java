package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.item.GetItemDto;
import lozm.core.dto.item.PostItemDto;
import lozm.core.dto.item.PutItemDto;
import lozm.core.dto.orders.GetOrdersDto;
import lozm.core.dto.orders.PostOrdersDto;
import lozm.core.dto.orders.PutOrdersDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/item")
@RestController
@RequiredArgsConstructor
public class ItemAPIController {

    private final ItemService itemService;


    @GetMapping
    public APIResponseDto getItem() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetItemDto.Response> result = itemService.findAllItems();
            resDto.setSuccess(true);
            resDto.setData(result);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postItem(@RequestBody @Valid PostItemDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            itemService.save(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public APIResponseDto putItem(@RequestBody @Valid PutItemDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            itemService.update(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
