package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.global.jwt.SignInfo;
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

    private final ItemAPIService itemAPIService;
    private final SignInfo signInfo;


    @GetMapping
    public ApiResponseDto getItemList() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemAPIService.getItemList());
    }

    @GetMapping(value = "/{itemId}")
    public ApiResponseDto getItemDetail(@PathVariable(value = "itemId") Long itemId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemAPIService.getItemDetail(itemId));
    }

    @GetMapping(value = "/clothing/{itemType}")
    public ApiResponseDto getClothing(@PathVariable(value = "itemType") String itemType) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, itemAPIService.getClothing(itemType));
    }

    @PostMapping
    public ApiResponseDto postItem(@RequestBody @Valid PostItemDto.Request reqDto) throws Exception {
        reqDto.setCreatedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, itemAPIService.postItem(reqDto));
    }

    @PutMapping
    public ApiResponseDto putItem(@RequestBody @Valid PutItemDto.Request reqDto) throws Exception {
        reqDto.setModifiedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, itemAPIService.putItem(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteItem(@RequestBody @Valid DeleteItemDto.Request reqDto) throws Exception {
        reqDto.setModifiedBy(signInfo.getId());
        return ApiResponseDto.createException(ApiResponseCode.OK, itemAPIService.deleteItem(reqDto));
    }

}
