package Les17;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world";
    }
}
//Аннотация @Controller
//1) Помечает класс - контроллер
//2) Наследуется от аннотации @Component
//3) Поэтому @ComponentScan работает с @Controller так же, как и просто с @Component
//4) @Controller - тот же самый @Component, но с дополнительными возможностями

//Методы контроллеров
//1) Методов может быть несколько в контроллере
//2) Обычно (но не всегда), каждый метод соответствует одному URL'у
//3) Обычно (но не всегда), методы возвращают строку (String) - название представления,
// которое надо показать пользователю
//4) У метода может быть любое название

//Маппинги
//@GetMapping
//@PostMapping
//@PutMapping
//@DeleteMapping
//@PatchMapping
// Иногда пишут (устаревший вариант): @RequestMapping(method = RequestMethod.GET)
//1) Связывают метод контроллера с адресом, по которому можно к этому методу обратиться
// (из браузера например)
//2) Всего 5 разных видом маппинга - в зависимости от того, какой HTTP запрос
// (с каким HTTP методом) должен прийти в этот метод контроллера

//@RequestMapping на классе
//Эта аннотация может использоваться на методе (устаревший способ - лучше использовать
// новые аннотации):
//@RequestMapping (value = "/new", method = RequestMethod.GET)
//public String newPerson (Model model) {
//Но может так же использоваться на классе:
//@Controller
//@RequestMapping("/people")                   /people/new
//public class PersonController {
// В этом случае URL адреса всех методов будут обязательно иметь в себе /people в начале