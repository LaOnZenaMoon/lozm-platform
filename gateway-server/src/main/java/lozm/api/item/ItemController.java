package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.item.DeleteItemDto;
import lozm.object.dto.item.PostItemDto;
import lozm.object.dto.item.PutItemDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/item")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping
    public ApiResponseDto getItemList() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemService.getItemList());
    }

    @GetMapping(value = "/{itemId}")
    public ApiResponseDto getItemDetail(@PathVariable(value = "itemId") Long itemId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemService.getItemDetail(itemId));
    }

    @GetMapping(value = "/clothing/{itemType}")
    public ApiResponseDto getClothing(@PathVariable(value = "itemType") String itemType) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemService.getClothing(itemType));
    }

    @PostMapping
    public ApiResponseDto postItem(@RequestBody @Valid PostItemDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemService.postItem(reqDto));
    }

    @PutMapping
    public ApiResponseDto putItem(@RequestBody @Valid PutItemDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemService.putItem(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteItem(@RequestBody @Valid DeleteItemDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemService.deleteItem(reqDto));
    }

}
