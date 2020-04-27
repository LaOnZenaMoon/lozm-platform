package lozm.item;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.item.GetClothingDto;
import lozm.dto.item.GetItemDto;
import lozm.dto.item.PostItemDto;
import lozm.dto.item.PutItemDto;
import lozm.vo.item.ItemVo;
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
            ItemVo itemvo = ItemVo.builder()
                    .name(reqDto.getName())
                    .price(reqDto.getPrice())
                    .quantity(reqDto.getQuantity())
                    .type(reqDto.getType())
                    .contents(reqDto.getContents())
                    .size(reqDto.getSize())
                    .storeId(reqDto.getStoreId())
                    .build();

            itemService.save(itemvo);
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
            ItemVo itemvo = ItemVo.builder()
                    .id(reqDto.getId())
                    .name(reqDto.getName())
                    .price(reqDto.getPrice())
                    .quantity(reqDto.getQuantity())
                    .contents(reqDto.getContents())
                    .size(reqDto.getSize())
                    .flag(reqDto.getFlag())
                    .build();

            itemService.update(itemvo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}