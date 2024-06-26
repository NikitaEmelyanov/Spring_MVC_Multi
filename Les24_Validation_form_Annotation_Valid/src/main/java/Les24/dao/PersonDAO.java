package Les24.dao;

import Les24.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom",24,"tom@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob",32,"Bobikus@yandex.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike",16,"MikeLoveJava@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy",52,"OldKaty@yahoo.com"));
    }


    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId()==id);
                //removeIf() - метод удаляющий объект по редикату, с помощью лямбды выражения
        // нужно указать условие удаление
    }

}
