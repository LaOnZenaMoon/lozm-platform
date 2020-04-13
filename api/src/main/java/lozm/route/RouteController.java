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
        return "home";
    }

    @GetMapping(value = "/manage/item")
    public String managingItem(ModelMap modelMap) {
        return "manageItem";
    }

    @GetMapping(value = "/signOut")
    public String signOut(ModelMap modelMap) {
        httpSession.removeAttribute(USER.name());
        httpSession.removeAttribute(PREV_PAGE.name());

        return "signIn";
    }

}
