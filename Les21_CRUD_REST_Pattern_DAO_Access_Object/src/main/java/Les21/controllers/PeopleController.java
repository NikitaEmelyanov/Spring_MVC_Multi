package Les21.controllers;

import Les21.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO; //Внедрение объекта DAO, чтобы можно было внедрять зависимости
@Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO; // Лучше внедрять зависимости через конструктор чем напрямую
    // через импортированную переменную
    }

    @GetMapping()
    public String index(Model model){
        // Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show (@PathVariable ("id") int id, Model model) {
        // С помощью аннотации @PathVariable можно будет извлечь id из URL'а и получим к нему
        // доступ внутри этого метода
        //Получим одного человека по id из DAO и передадим этого человека в представление
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }
}
