package Les31.dao;

import Les31.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    // Реализация RowMapper (обязательно из org.springframework.jdbc.core.RowMapper). RowMapper
    // необходимо параметризовать, чтобы он возвращал не общий объект класса Object, а объект
    // класса Person
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));
        person.setAge(resultSet.getInt("age"));

        return person;
    }
}
