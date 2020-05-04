package lozm.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.coupon.CouponAPIController;
import lozm.dto.APIResponseDto;
import lozm.dto.coupon.GetCouponDto;
import lozm.dto.coupon.GetCouponUserDto;
import lozm.dto.item.GetClothingDto;
import lozm.dto.store.GetStoreDto;
import lozm.store.StoreAPIController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

import static lozm.code.SessionType.USER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RouteController {

    private final HttpSession httpSession;
    private final StoreAPIController storeAPIController;
    private final CouponAPIController couponAPIController;


    @GetMapping(value = "/home")
    public String home(ModelMap modelMap) {
        return "pages/home";
    }

    @GetMapping(value = "/manage/store")
    public String manageStore(ModelMap modelMap) {
        return "pages/store/store";
    }

    @GetMapping(value = "/manage/store/{storeId}")
    public String manageItem(ModelMap modelMap, @PathVariable(value = "storeId") Long storeId) {
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

        return "pages/store/storeDetail";
    }

    @GetMapping(value = "/manage/item")
    public String manageItem(ModelMap modelMap) {
        return "pages/item/item";
    }

    @GetMapping(value = "/manage/coupon")
    public String manageCoupon(ModelMap modelMap) {
        return "pages/coupon/coupon";
    }

    @GetMapping(value = "/manage/coupon/{couponId}")
    public String manageCoupon(ModelMap modelMap, @PathVariable(value = "couponId") Long couponId) {

        APIResponseDto getCouponDetail = couponAPIController.getCouponDetail(couponId);
        GetCouponDto couponDetail = (GetCouponDto) getCouponDetail.getData();
        modelMap.addAttribute("couponDetail", couponDetail);

        APIResponseDto getCouponUser = couponAPIController.getCouponUser(couponId);
        GetCouponUserDto.Response couponUser = (GetCouponUserDto.Response) getCouponUser.getData();
        List<GetCouponUserDto> couponUserList = couponUser.getList();
        modelMap.addAttribute("couponUserList", couponUserList);

        return "pages/coupon/couponDetail";
    }

    @GetMapping(value = "/manage/orders")
    public String manageOrders(ModelMap modelMap) {
        return "pages/orders/orders";
    }

    @GetMapping(value = "/setting/user")
    public String settingUser(ModelMap modelMap) {
        return "pages/user/user";
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
