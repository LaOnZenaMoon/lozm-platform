package lozm.api.route;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping
    public String home(ModelMap modelMap) {
        return "index";
    }

    @GetMapping(value = "/manageItem")
    public String managingItem(ModelMap modelMap) {
        return "manageItem";
    }

    @GetMapping(value = "/signIn")
    public String signIn(ModelMap modelMap) {
        return "signIn";
    }

}
