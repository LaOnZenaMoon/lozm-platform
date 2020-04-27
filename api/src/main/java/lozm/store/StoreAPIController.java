package lozm.store;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.item.GetClothingDto;
import lozm.dto.item.GetItemDto;
import lozm.dto.item.PostItemDto;
import lozm.dto.item.PutItemDto;
import lozm.dto.store.DeleteStoreDto;
import lozm.dto.store.GetStoreDto;
import lozm.dto.store.PostStoreDto;
import lozm.dto.store.PutStoreDto;
import lozm.entity.store.Store;
import lozm.item.ItemService;
import lozm.vo.item.ItemVo;
import lozm.vo.store.StoreVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/store")
@RestController
@RequiredArgsConstructor
public class StoreAPIController {

    private final StoreService storeService;
    private final ItemService itemService;


    @GetMapping
    public APIResponseDto getStore() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetStoreDto> storeList = storeService.findAllStores();
            GetStoreDto.Response storeResDto = new GetStoreDto.Response();
            storeResDto.setList(storeList);

            resDto.setSuccess(true);
            resDto.setData(storeResDto);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @GetMapping(value = "/{storeId}")
    public APIResponseDto getStoreDetail(@PathVariable(value = "storeId") Long storeId) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            StoreVo storeVo = StoreVo.builder()
                    .id(storeId)
                    .build();

            Store store = storeService.findById(storeVo);
            GetStoreDto storeResDto = new GetStoreDto(store.getId(), store.getName(), store.getTelNumber(), store.getInfo());
            resDto.setData(storeResDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @GetMapping(value = "/{storeId}/clothing/{itemType}")
    public APIResponseDto getStoreClothing(
            @PathVariable(value = "storeId") Long storeId,
            @PathVariable(value = "itemType") String itemType
    ) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            ItemVo itemVo = ItemVo.builder()
                    .storeId(storeId)
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
    public APIResponseDto postStore(@RequestBody @Valid PostStoreDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            StoreVo storeVo = StoreVo.builder()
                    .name(reqDto.getName())
                    .telNumber(reqDto.getTelNumber())
                    .info(reqDto.getInfo())
                    .build();

            storeService.save(storeVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public APIResponseDto putStore(@RequestBody @Valid PutStoreDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            StoreVo storeVo = StoreVo.builder()
                    .id(reqDto.getId())
                    .name(reqDto.getName())
                    .telNumber(reqDto.getTelNumber())
                    .info(reqDto.getInfo())
                    .build();

            storeService.update(storeVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @DeleteMapping
    public APIResponseDto deleteStore(@RequestBody @Valid DeleteStoreDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            for(DeleteStoreDto dto : reqDto.getList()) {
                StoreVo storeVo = StoreVo.builder()
                    .id(dto.getId())
                    .build();

                storeService.delete(storeVo);
            }

            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
