package lozm.api.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.object.dto.coupon.GetCouponDto;
import lozm.object.dto.coupon.GetCouponUserDto;
import lozm.object.dto.item.GetClothingDto;
import lozm.object.dto.store.GetStoreDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

import static lozm.object.code.SessionType.USER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RouteController {

    private final HttpSession httpSession;
    private final RouteService routeService;


    @GetMapping(value = "/home")
    public String home(ModelMap modelMap) {
        return "pages/home";
    }

    @GetMapping(value = "/manage/board")
    public String manageBoard(ModelMap modelMap) {
        return "pages/board/board";
    }

    @GetMapping(value = "/manage/store")
    public String manageStore(ModelMap modelMap) {
        return "pages/store/store";
    }

    @GetMapping(value = "/manage/store/{storeId}")
    public String manageItem(ModelMap modelMap, @PathVariable(value = "storeId") Long storeId) throws Exception {
        log.debug("Store ID: "+storeId);

        GetStoreDto getStoreDetail = routeService.getStoreDetail(storeId);
        modelMap.addAttribute("storeDetail", getStoreDetail);

        GetClothingDto.Response getOuter = routeService.getStoreClothing(storeId, "OUTER");
        modelMap.addAttribute("outerList", getOuter.getList());

        GetClothingDto.Response getTop = routeService.getStoreClothing(storeId, "TOP");
        modelMap.addAttribute("topList", getTop.getList());

        GetClothingDto.Response getBottom = routeService.getStoreClothing(storeId, "BOTTOM");
        modelMap.addAttribute("bottomList", getBottom.getList());

        GetClothingDto.Response getShoes = routeService.getStoreClothing(storeId, "SHOES");
        modelMap.addAttribute("shoesList", getShoes.getList());

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
    public String manageCoupon(ModelMap modelMap, @PathVariable(value = "couponId") Long couponId) throws Exception {

        GetCouponDto getCouponDetail = routeService.getCouponDetail(couponId);
        modelMap.addAttribute("couponDetail", getCouponDetail);

        GetCouponUserDto.Response getCouponUser = routeService.getCouponUser(couponId);
        modelMap.addAttribute("couponUserList", getCouponUser.getList());

        return "pages/coupon/couponDetail";
    }

    @GetMapping(value = "/manage/delivery")
    public String manageDelivery(ModelMap modelMap) {
        return "pages/delivery/delivery";
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
