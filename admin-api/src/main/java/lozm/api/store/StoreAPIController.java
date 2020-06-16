package lozm.api.store;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.item.GetClothingDto;
import lozm.object.dto.store.DeleteStoreDto;
import lozm.object.dto.store.GetStoreDto;
import lozm.object.dto.store.PostStoreDto;
import lozm.object.dto.store.PutStoreDto;
import lozm.entity.store.Store;
import lozm.api.item.ItemService;
import lozm.object.vo.item.ItemVo;
import lozm.object.vo.store.StoreVo;
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
    public ApiResponseDto getStore() {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            List<GetStoreDto> storeList = storeService.getStoreList();
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
    public ApiResponseDto getStoreDetail(@PathVariable(value = "storeId") Long storeId) {
        ApiResponseDto resDto = new ApiResponseDto<>();

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
    public ApiResponseDto getStoreClothing(
            @PathVariable(value = "storeId") Long storeId,
            @PathVariable(value = "itemType") String itemType
    ) {
        ApiResponseDto resDto = new ApiResponseDto<>();

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
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public ApiResponseDto postStore(@RequestBody @Valid PostStoreDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

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
    public ApiResponseDto putStore(@RequestBody @Valid PutStoreDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            StoreVo storeVo = StoreVo.builder()
                    .id(reqDto.getId())
                    .name(reqDto.getName())
                    .telNumber(reqDto.getTelNumber())
                    .info(reqDto.getInfo())
                    .flag(1)
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
    public ApiResponseDto deleteStore(@RequestBody @Valid DeleteStoreDto.Request reqDto) {
        ApiResponseDto resDto = new ApiResponseDto<>();

        try {
            for(DeleteStoreDto dto : reqDto.getList()) {
                StoreVo storeVo = StoreVo.builder()
                    .id(dto.getId())
                    .flag(0)
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
