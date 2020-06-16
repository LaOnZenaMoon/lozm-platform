package lozm.api.store;

import lombok.RequiredArgsConstructor;
import lozm.entity.store.Store;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.item.GetClothingDto;
import lozm.object.dto.store.DeleteStoreDto;
import lozm.object.dto.store.GetStoreDto;
import lozm.object.dto.store.PostStoreDto;
import lozm.object.dto.store.PutStoreDto;
import lozm.object.vo.item.ItemVo;
import lozm.object.vo.store.StoreVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/store")
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;


    @GetMapping
    public ApiResponseDto getStore() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeService.getStore());
    }

    @GetMapping(value = "/{storeId}")
    public ApiResponseDto getStoreDetail(@PathVariable(value = "storeId") Long storeId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeService.getStoreDetail(storeId));
    }

    @GetMapping(value = "/{storeId}/clothing/{itemType}")
    public ApiResponseDto getStoreClothing(
            @PathVariable(value = "storeId") Long storeId,
            @PathVariable(value = "itemType") String itemType
    ) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeService.getStoreClothing(storeId, itemType));
    }

    @PostMapping
    public ApiResponseDto postStore(@RequestBody @Valid PostStoreDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeService.postStore(reqDto));
    }

    @PutMapping
    public ApiResponseDto putStore(@RequestBody @Valid PutStoreDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeService.putStore(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteStore(@RequestBody @Valid DeleteStoreDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeService.deleteStore(reqDto));
    }

}
