package lozm.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.dto.APIResponseDto;
import lozm.dto.item.GetClothingDto;
import lozm.dto.store.GetStoreDto;
import lozm.item.ItemAPIController;
import lozm.store.StoreAPIController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

import java.util.List;

import static lozm.code.SessionType.PREV_PAGE;
import static lozm.code.SessionType.USER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RouteController {

    private final HttpSession httpSession;
    private final StoreAPIController storeAPIController;
    private final ItemAPIController itemAPIController;


    @GetMapping(value = "/home")
    public String home(ModelMap modelMap) {
        return "pages/home";
    }

    @GetMapping(value = "/manage/store")
    public String managingStore(ModelMap modelMap) {
        return "pages/store/manageStore";
    }

    @GetMapping(value = "/manage/store/{storeId}")
    public String managingItem(ModelMap modelMap, @PathVariable(value = "storeId") Long storeId) {
        log.debug("Store ID: "+storeId);

        APIResponseDto getStoreDetail = storeAPIController.getStoreDetail(storeId);
        GetStoreDto storeDetail = (GetStoreDto) getStoreDetail.getData();
        modelMap.addAttribute("storeDetail", storeDetail);

        APIResponseDto getOuter = storeAPIController.getStoreClothing(storeId, "OUTER");
        GetClothingDto.Response outer = (GetClothingDto.Response) getOuter.getData();
        List<GetClothingDto> outerList = outer.getList();
        modelMap.addAttribute("outerList", outerList);

        APIResponseDto getTop = storeAPIController.getStoreClothing(storeId, "TOP");
        GetClothingDto.Response top = (GetClothingDto.Response) getTop.getData();
        List<GetClothingDto> topList = top.getList();
        modelMap.addAttribute("topList", topList);

        APIResponseDto getBottom = storeAPIController.getStoreClothing(storeId, "BOTTOM");
        GetClothingDto.Response bottom = (GetClothingDto.Response) getBottom.getData();
        List<GetClothingDto> bottomList = bottom.getList();
        modelMap.addAttribute("bottomList", bottomList);

        APIResponseDto getShoes = storeAPIController.getStoreClothing(storeId, "SHOES");
        GetClothingDto.Response shoes = (GetClothingDto.Response) getShoes.getData();
        List<GetClothingDto> shoesList = shoes.getList();
        modelMap.addAttribute("shoesList", shoesList);

        return "pages/store/manageStoreDetail";
    }

    @GetMapping(value = "/manage/item")
    public String managingItem(ModelMap modelMap) {
        return "pages/item/manageItem";
    }

    @GetMapping(value = "/sign/in")
    public String signIn(ModelMap modelMap) {
        return "pages/sign/signIn";
    }

    @GetMapping(value = "/sign/out")
    public String signOut(ModelMap modelMap) {
        httpSession.removeAttribute(USER.name());
//        httpSession.removeAttribute(PREV_PAGE.name());

        return this.signIn(modelMap);
    }

}
