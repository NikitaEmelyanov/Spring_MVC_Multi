package Les21.dao;

import Les21.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private  List<Person> people;

    {                                              // {
        people = new ArrayList<>();                //    <--- Блок инициализации, можно заменить
                                                   // }       конструктором (функционал один и тот же)
        people.add(new Person(++PEOPLE_COUNT,"Tom"));
        people.add(new Person(++PEOPLE_COUNT,"Bob"));
        people.add(new Person(++PEOPLE_COUNT,"Mike"));
        people.add(new Person(++PEOPLE_COUNT,"Katy"));
        //Обычно в БД id назначается автоматически и при добавлении новой переменной, id предыдущей
        // переменой инкриминируется, при том каждая переменная статическая, что обеспечит
        // персональный id для каждой переменной (Человека)
    }
    public List<Person> index (){
        return people;
    }
    public Person show (int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        //Было использовано лямбда выражение, для того чтобы сократить код, но можно было
        // использовать цикл for. В данном лямбде выражении указано: Отладка, фильтрация человека
        // по его id, чтобы id ровнялся тому, который пришел в качестве аргумента. Затем находим
        // этого человека если он есть, если его нет возвращается null.
    }



}
