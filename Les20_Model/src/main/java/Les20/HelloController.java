package Les20;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 6. Параметры GET запроса. Аннотация @RequestParam.
 */

@Controller
public class HelloController {
    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world";
    }
}
//________________________Модель - Контейнер для данных нашего приложения___________________________

//    HTML Форма (и не только) ---------> Контроллер ---------> Представление
//                              Модель      |    |       Модель
//                                          |    | Модель
//                                        База Данных