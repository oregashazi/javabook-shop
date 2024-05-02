package onlineshop;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {
    private static Logger log = LogManager.getLogger(ShopController.class);

    @GetMapping(value = {"/"})
    public String root(Model model) {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html"})
    public String homePage(Model model) {
        return "index";
    }

    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(@PathVariable("name") String name, HttpSession session) {
        return name;
    }

}
