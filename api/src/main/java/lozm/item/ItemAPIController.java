package lozm.item;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.item.*;
import lozm.dto.store.DeleteStoreDto;
import lozm.vo.item.ItemVo;
import lozm.vo.store.StoreVo;
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

            resDto.setSuccess(true);
            resDto.setData(itemResDto);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @GetMapping(value = "/{storeId}")
    public APIResponseDto getItemListByStoreId(@PathVariable(value = "storeId") Long storeId) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            ItemVo itemVo = ItemVo.builder()
                    .storeId(storeId)
                    .build();

            List<GetItemDto> itemList = itemService.getItemListByStoreId(itemVo);
            GetItemDto.Response itemResDto = new GetItemDto.Response();
            itemResDto.setList(itemList);

            resDto.setSuccess(true);
            resDto.setData(itemResDto);
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
