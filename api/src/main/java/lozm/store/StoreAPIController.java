package lozm.store;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.item.GetClothingDto;
import lozm.dto.item.GetItemDto;
import lozm.dto.item.PostItemDto;
import lozm.dto.item.PutItemDto;
import lozm.dto.store.GetStoreDto;
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


    @GetMapping
    public APIResponseDto getStore() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetStoreDto> storeList = storeService.findAllItems();
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

            GetStoreDto storeResDto = storeService.findById(storeVo);
            resDto.setData(storeResDto);
            resDto.setSuccess(true);
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
                    .build();
            storeService.save(itemvo);
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

            storeService.update(itemvo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
