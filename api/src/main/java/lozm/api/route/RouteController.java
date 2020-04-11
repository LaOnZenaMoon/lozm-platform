package lozm.api.route;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static lozm.core.code.SessionType.PREV_PAGE;
import static lozm.core.code.SessionType.USER;

@Controller
@RequiredArgsConstructor
public class RouteController {

    private final HttpSession httpSession;


    @GetMapping
    public String home(ModelMap modelMap) {
        return "index";
    }

    @GetMapping(value = "/manageItem")
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
