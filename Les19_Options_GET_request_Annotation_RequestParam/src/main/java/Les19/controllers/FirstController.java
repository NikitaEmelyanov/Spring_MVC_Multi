package Les19.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        //Теперь если запустить сервер и добавить в адресную строку /first/hello мы попадаем на
        // страницу /hello, далее если добавить после hello в адресную строку
        // ?name=Tom&surname=Jones (параметры) в консоль будет выведено сообщение Hello, Tom Jones,
        // если параметры не ввести в консоль будет выведено Hello, null null

//     public String helloPage (@RequestParam ("name") String name,
//                              @RequestParam ("surname") String surname ) {
//     System.out.println("Hello, "+name + " "+surname);
//     return "first/hello";
        //Реализация с помощью аннотации @RequestParam, но если не передавать параметры сервер
        // выдаст ошибку HTTP Status 400 - Bad Request, в отличии от предыдущей реализации, но
        // если использовать следующую конструкцию, не передавая параметров ошибка пропадет, а в
        // консоль выведется Hello, null null
//     public String helloPage (@RequestParam(value = "name",required = false) String name,
//                              @RequestParam(value = "surname",required = false) String surname ){
//     System.out.println("Hello, "+name + " "+surname);
//     return "first/hello";

        System.out.println("Hello, "+name + " "+surname);

        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }
}
// <a href="first/goodbye">Say goodbye </a> or <a href="/exit">Exit</a>
// or <a href ="/first/hello?name=Tom&surname=Jones">Request with parameters</a>
// <!-- Так же можно указывать параметр сразу в html файле --->