package lozm.api.store;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;
import lozm.object.dto.store.DeleteStoreDto;
import lozm.object.dto.store.PostStoreDto;
import lozm.object.dto.store.PutStoreDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/store")
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreAPIService storeAPIService;


    @GetMapping
    public ApiResponseDto getStore() throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeAPIService.getStore());
    }

    @GetMapping(value = "/{storeId}")
    public ApiResponseDto getStoreDetail(@PathVariable(value = "storeId") Long storeId) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeAPIService.getStoreDetail(storeId));
    }

    @GetMapping(value = "/{storeId}/clothing/{itemType}")
    public ApiResponseDto getStoreClothing(
            @PathVariable(value = "storeId") Long storeId,
            @PathVariable(value = "itemType") String itemType
    ) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeAPIService.getStoreClothing(storeId, itemType));
    }

    @PostMapping
    public ApiResponseDto postStore(@RequestBody @Valid PostStoreDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeAPIService.postStore(reqDto));
    }

    @PutMapping
    public ApiResponseDto putStore(@RequestBody @Valid PutStoreDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeAPIService.putStore(reqDto));
    }

    @DeleteMapping
    public ApiResponseDto deleteStore(@RequestBody @Valid DeleteStoreDto.Request reqDto) throws Exception {
        return ApiResponseDto.createException(ApiResponseCode.OK, storeAPIService.deleteStore(reqDto));
    }

}
