package Les17.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(){
        return "first/hello";
        //Все представления, которые относятся к first контроллеру будут лежать в папке first
        // внутри папки views (Это хорошая практика, так удобно делать)
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }
}
