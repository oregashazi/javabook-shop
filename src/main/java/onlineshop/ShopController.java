package onlineshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
    @GetMapping(value = {"/"})
    public String start(Model model) {
        return "index";
    }

}
