package lozm.api.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lozm.api.board.BoardAPIService;
import lozm.object.code.BoardType;
import lozm.object.code.ContentType;
import lozm.object.dto.board.GetBoardDto;
import lozm.object.dto.board.GetCommentDto;
import lozm.object.dto.coupon.GetCouponDto;
import lozm.object.dto.coupon.GetCouponUserDto;
import lozm.object.dto.item.GetClothingDto;
import lozm.object.dto.store.GetStoreDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static lozm.object.code.SessionType.USER;

@Controller @RequestMapping("/pages")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final BoardAPIService boardAPIService;


    @GetMapping("/home")
    public String home(ModelMap modelMap) {
        return "pages/home";
    }

    @GetMapping("/manage/board")
    public String manageBoard(ModelMap modelMap) {
        setBoardInfo(modelMap);

        return "pages/board/board";
    }

    @GetMapping("/manage/board/{boardId}")
    public String manageBoardDetail(ModelMap modelMap, @PathVariable(value = "boardId") Long boardId) throws Exception {
        setBoardInfo(modelMap);
//        GetCommentDto.Response commentList = boardAPIService.getComment(boardId);
//        modelMap.addAttribute("commentList", commentList.getList());
//

//
//        List<ContentType> commentTypeList = Arrays.asList(ContentType.values());
//        modelMap.addAttribute("commentTypeList", commentTypeList);

        return "pages/board/boardDetail";
    }

    @GetMapping("/manage/store")
    public String manageStore(ModelMap modelMap) {
        return "pages/store/store";
    }

    @GetMapping("/manage/store/{storeId}")
    public String manageItem(ModelMap modelMap, @PathVariable(value = "storeId") Long storeId) throws Exception {
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

    @GetMapping("/manage/item")
    public String manageItem(ModelMap modelMap) {
        return "pages/item/item";
    }

    @GetMapping("/manage/coupon")
    public String manageCoupon(ModelMap modelMap) {
        return "pages/coupon/coupon";
    }

    @GetMapping("/manage/coupon/{couponId}")
    public String manageCoupon(ModelMap modelMap, @PathVariable(value = "couponId") Long couponId) throws Exception {

        GetCouponDto getCouponDetail = routeService.getCouponDetail(couponId);
        modelMap.addAttribute("couponDetail", getCouponDetail);

        GetCouponUserDto.Response getCouponUser = routeService.getCouponUser(couponId);
        modelMap.addAttribute("couponUserList", getCouponUser.getList());

        return "pages/coupon/couponDetail";
    }

    @GetMapping("/manage/delivery")
    public String manageDelivery(ModelMap modelMap) {
        return "pages/delivery/delivery";
    }

    @GetMapping("/manage/orders")
    public String manageOrders(ModelMap modelMap) {
        return "pages/orders/orders";
    }

    @GetMapping("/setting/user")
    public String settingUser(ModelMap modelMap) {
        return "pages/user/user";
    }

    @GetMapping("/sign/in")
    public String signIn(ModelMap modelMap) {
        return "pages/sign/signIn";
    }

    private void setBoardInfo(ModelMap modelMap) {
        List<BoardType> boardTypeList = Arrays.asList(BoardType.values());
        modelMap.addAttribute("boardTypeList", boardTypeList);

        List<ContentType> contentTypeList = Arrays.asList(ContentType.values());
        modelMap.addAttribute("contentTypeList", contentTypeList);
    }

}