package lozm.api.store;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
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


@CrossOrigin("*")
@RestController @RequestMapping(value = "/api/store")
@RequiredArgsConstructor
public class StoreAPIController {

    private final StoreService storeService;
    private final ItemService itemService;


    @GetMapping
    public ApiResponseDto getStore() throws Exception {
        List<GetStoreDto> storeList = storeService.getStoreList();

        GetStoreDto.Response resDto = new GetStoreDto.Response();
        resDto.setList(storeList);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping(value = "/{storeId}")
    public ApiResponseDto getStoreDetail(@PathVariable(value = "storeId") Long storeId) throws Exception {
        StoreVo storeVo = StoreVo.builder()
                .id(storeId)
                .build();

        Store store = storeService.findById(storeVo);
        GetStoreDto resDto = new GetStoreDto(store.getId(), store.getName(), store.getTelNumber(), store.getInfo());

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping(value = "/{storeId}/clothing/{itemType}")
    public ApiResponseDto getStoreClothing(
            @PathVariable(value = "storeId") Long storeId,
            @PathVariable(value = "itemType") String itemType
    ) throws Exception {
        ItemVo itemVo = ItemVo.builder()
                .storeId(storeId)
                .type(itemType)
                .build();

        List<GetClothingDto> clothingList = itemService.getClothingList(itemVo);

        GetClothingDto.Response resDto = new GetClothingDto.Response();
        resDto.setList(clothingList);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postStore(@RequestBody @Valid PostStoreDto.Request reqDto) throws Exception {
        StoreVo storeVo = StoreVo.builder()
                .name(reqDto.getName())
                .telNumber(reqDto.getTelNumber())
                .info(reqDto.getInfo())
                .createdBy(reqDto.getCreatedBy())
                .build();

        storeService.save(storeVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putStore(@RequestBody @Valid PutStoreDto.Request reqDto) throws Exception {
        StoreVo storeVo = StoreVo.builder()
                .id(reqDto.getId())
                .name(reqDto.getName())
                .telNumber(reqDto.getTelNumber())
                .info(reqDto.getInfo())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

        storeService.update(storeVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteStore(@RequestBody @Valid DeleteStoreDto.Request reqDto) throws Exception {
        for(DeleteStoreDto dto : reqDto.getList()) {
            StoreVo storeVo = StoreVo.builder()
                .id(dto.getId())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

            storeService.delete(storeVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
