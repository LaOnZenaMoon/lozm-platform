package lozm.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.dto.APIResponseDto;
import lozm.dto.store.GetStoreDto;
import lozm.item.ItemAPIController;
import lozm.store.StoreAPIController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

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

        APIResponseDto getStoreDetailResDto = storeAPIController.getStoreDetail(storeId);
        GetStoreDto storeDetail = (GetStoreDto) getStoreDetailResDto.getData();
        modelMap.addAttribute("storeDetail", storeDetail);



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
