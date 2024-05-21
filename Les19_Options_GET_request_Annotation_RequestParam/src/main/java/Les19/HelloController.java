package Les19;

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
//_____________Параметры GET- запроса в Spring Framework можно получить двумя способами:____________
//1) С помощью объекта HttpServletRequest
// @GetMapping("/hello")
// public String helloPage (HttpServletRequest request) {
//     String name = request.getParameter (s: "name");         <--- Первый метод дает доступ ко всем
//     //Работаем с пришедшим от пользователя параметром            сведениям HTTP запроса
//     return "first/hello";

//2) С Помощью аннотации @RequestMapping
// public String helloPage (@RequestParam ("name") String name) {
//     String name = request.getParameter (s: "name");        <--- Второй метод дает доступ только к
//     //Работаем с пришедшим от пользователя параметром           параметрам HTTP запроса
//     return "first/hello";