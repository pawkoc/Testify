package pl.edu.agh.testify.composite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title","Testify");
        return "index";
    }
}
