package Les28.dao;
import Les28.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
@Autowired //Внедрение JdbcTemplate
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
       return jdbcTemplate.query("SELECT * FROM Person",new PersonMapper());
               //метод query() из JdbcTemplate - в качестве первого аргумента указывается sql
        // запрос, а в качестве второго указывается RowMapper (это такой объект, который
        // отображает объекты талицы в виде сущности и его нужно реализовать самостоятельно,
        // можно это сделать в отдельном классе). Поскольку в RowMapper  мы делаем ничего сложно,
        // а только назначаем поля с помощью сеттеров, получив название, его реализация не
        // обязательна, так как в Spring есть готовый RowMapper, который называется
        // BeanPropertyRowMapper<>() и в качестве аргумента конструктора передаем нужный класс
        // (Person.class). В дальнейших методах мы будем использовать его.
    }

    public Person show(int id) {
return jdbcTemplate.query("SELECT * FROM Person WHERE Id=?",new Object[]{id},
        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//Изначально выдается ошибка, так как при вызове метода query возвращается список , а нам необходимо
// возвращать одного человека, это можно сделать с помощью лямбды выражения c помощью метода
// findAny() если объект есть в списке вернуть его, если его нет - вернуть null с помощью  orElse.
    }

    public void save(Person person) {
    jdbcTemplate.update("INSERT INTO Person VALUES (1, ?, ?, ?)",
            person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?,age=?,email=? WHERE id=?",
                updatedPerson.getName(),updatedPerson.getAge(),updatedPerson.getEmail(),id);
        //В данном методе синтаксис передачи данных с помощью vararg'ов в отличии от метода show(),
        // где использовался массив, причина реализации разных синтаксисов не ясна, возможно это
        // из-за того, что в качестве последнего аргумента в методе show() передается
        // BeanPropertyRowMapper<>()

    }

    public void delete(int id) {
    jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
}