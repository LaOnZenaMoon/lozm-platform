package lozm.route;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static lozm.code.SessionType.PREV_PAGE;
import static lozm.code.SessionType.USER;

@Controller
@RequiredArgsConstructor
public class RouteController {

    private final HttpSession httpSession;


    @GetMapping(value = "/home")
    public String home(ModelMap modelMap) {
        return "pages/home";
    }

    @GetMapping(value = "/store/manage")
    public String managingStore(ModelMap modelMap) {
        return "pages/store/manageStore";
    }

    @GetMapping(value = "/item/manage")
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
        httpSession.removeAttribute(PREV_PAGE.name());

        return this.signIn(modelMap);
    }

}
