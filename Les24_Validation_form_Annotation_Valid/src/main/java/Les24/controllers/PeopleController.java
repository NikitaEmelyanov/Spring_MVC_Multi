package Les24.controllers;


import Les24.dao.PersonDAO;
import Les24.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        //Аннотация @Valid будет валидировать значения пришедшие из формы и проверять подходят ли
        // они под все требования класса Person, если нет то все ошибки будут помещаться в
        // BindingResult, его необходимо всегда указывать в качестве второго аргумента при
        // аннотации @Valid
        if (bindingResult.hasErrors())
            return "people/new";
        //Проверка на содержание bindingResult ошибок с помощью встроенного в него метода
        // hasErrors(). Если ошибки есть в данном случае возвращается создание нового человека
        personDAO.save(person);
        return  "redirect:/people";
        }
    @GetMapping("/{id}/edit")
   public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
   }
   @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id,person);
        return "redirect:/people";
   }
   @DeleteMapping("/{id}")
    public String delete ( @PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
   }
}