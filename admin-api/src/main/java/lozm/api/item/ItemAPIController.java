package lozm.api.item;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.APIResponseDto;
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
    public APIResponseDto getItemList() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetItemDto> itemList = itemService.getItemList();
            GetItemDto.Response itemResDto = new GetItemDto.Response();
            itemResDto.setList(itemList);

            resDto.setData(itemResDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @GetMapping(value = "/{itemId}")
    public APIResponseDto getItemDetail(@PathVariable(value = "itemId") Long itemId) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            ItemVo itemVo = ItemVo.builder()
                    .id(itemId)
                    .build();

            GetItemDto itemDto = itemService.getItemDetail(itemVo);

            resDto.setData(itemDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }



    @GetMapping(value = "/clothing/{itemType}")
    public APIResponseDto getClothing(@PathVariable(value = "itemType") String itemType) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            ItemVo itemVo = ItemVo.builder()
                    .type(itemType)
                    .build();
            List<GetClothingDto> clothingList = itemService.getClothingList(itemVo);
            GetClothingDto.Response clothingResDto = new GetClothingDto.Response();
            clothingResDto.setList(clothingList);

            resDto.setSuccess(true);
            resDto.setData(clothingResDto);
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
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @DeleteMapping
    public APIResponseDto deleteItem(@RequestBody @Valid DeleteItemDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            for(DeleteItemDto dto : reqDto.getList()) {
                ItemVo itemVo = ItemVo.builder()
                        .id(dto.getId())
                        .flag(0)
                        .build();

                itemService.delete(itemVo);
            }

            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
