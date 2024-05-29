package Les31.dao;
import Les31.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES (?, ?, ?)",
            person.getName(),person.getAge(),person.getEmail());
    //При автоматической генерации значений нужно указать для Person какие значения поставляем
        // (name, age, email), а 1 (а 1, который отвечал за id) в VALUES теперь можно удалить
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

    //////////////////////////////////////////////////////
    /////// Тестируем производительность пакетной вставки
    //////////////////////////////////////////////////////
    public void testMultipleUpdate() { // Вставка 1000 людей с помощью обычного INSERT'а
    List<Person> people = create1000People();
    //Для удобства вынесли создание 1000 людей в отдельный метод
        long before = System.currentTimeMillis();

        for (Person person: people)
            jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)",
                    person.getId(), person.getName(),person.getAge(),person.getEmail());

        long after = System.currentTimeMillis();
        System.out.println("Time: " + (after-before));
    }

    public void testBatchUpdate() { // Вставка 1000 записей одним пакетом ( с помощью пакетной вставки)
    List<Person> people = create1000People();

        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
        // У jdbcTemplate есть специальный метод для пакетной вставки batchUpdate(), в качестве
        // аргумента нужно указать сам SQL запрос, а в качестве второго аргумента объект
        // анонимного класса BatchPreparedStatementSetter()) и реализовать 2 метода пришедшие их
        // этого класса.
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                //Реализация метода, в качестве первого аргумента объект PreparedStatement, для
                // возможности взаимодействия с полями человека, в качестве второго аргумента
                // передаем i - который указывает на текущего человека)
                ps.setInt(1, people.get(i).getId());
                ps.setString(2,people.get(i).getName());
                ps.setInt(3,people.get(i).getAge());
                ps.setString(4,people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });


        long after = System.currentTimeMillis();
        System.out.println("Time: " + (after-before));
    }

    private List<Person> create1000People() {
    List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++)
            people.add(new Person(i, "Name" + i,30,"test" +i + "@mail.ru"));

        return people;
    }
}