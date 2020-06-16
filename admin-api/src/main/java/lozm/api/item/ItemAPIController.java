package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.item.*;
import lozm.object.vo.item.ItemVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/item")
@RestController
@RequiredArgsConstructor
public class ItemAPIController {

    private final ItemService itemService;


    @GetMapping
    public ApiResponseDto getItemList() throws Exception {
        List<GetItemDto> itemList = itemService.getItemList();

        GetItemDto.Response resDto = new GetItemDto.Response();
        resDto.setList(itemList);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping(value = "/{itemId}")
    public ApiResponseDto getItemDetail(@PathVariable(value = "itemId") Long itemId) throws Exception {
        ItemVo itemVo = ItemVo.builder()
                .id(itemId)
                .build();

        GetItemDto resDto = itemService.getItemDetail(itemVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }



    @GetMapping(value = "/clothing/{itemType}")
    public ApiResponseDto getClothing(@PathVariable(value = "itemType") String itemType) throws Exception {
        ItemVo itemVo = ItemVo.builder()
                .type(itemType)
                .build();
        List<GetClothingDto> clothingList = itemService.getClothingList(itemVo);

        GetClothingDto.Response resDto = new GetClothingDto.Response();
        resDto.setList(clothingList);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postItem(@RequestBody @Valid PostItemDto.Request reqDto) throws Exception {
        ItemVo itemVo = ItemVo.builder()
                .name(reqDto.getName())
                .price(reqDto.getPrice())
                .quantity(reqDto.getQuantity())
                .type(reqDto.getType())
                .contents(reqDto.getContents())
                .size(reqDto.getSize())
                .storeId(reqDto.getStoreId())
                .build();

        itemService.save(itemVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putItem(@RequestBody @Valid PutItemDto.Request reqDto) throws Exception {
        ItemVo itemVo = ItemVo.builder()
                .id(reqDto.getId())
                .name(reqDto.getName())
                .price(reqDto.getPrice())
                .quantity(reqDto.getQuantity())
                .contents(reqDto.getContents())
                .size(reqDto.getSize())
                .flag(1)
                .build();

        itemService.update(itemVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteItem(@RequestBody @Valid DeleteItemDto.Request reqDto) throws Exception {
        for(DeleteItemDto dto : reqDto.getList()) {
            ItemVo itemVo = ItemVo.builder()
                    .id(dto.getId())
                    .flag(0)
                    .build();

            itemService.delete(itemVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
