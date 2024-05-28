package Les25.dao;

import Les25.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    //Обычно все эти переменные выносятся в отдельный конфигурационный файл, чтобы пользователь не
    // имел к этим данным доступ
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");  //   <--- Подключение в БД
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);  // <--- Ввод адреса,
        } catch (SQLException e) {                                              // логина и пароля
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            //Создание объекта запроса к БД на объекте соединения
            String SQL = "SELECT * FROM Person";
            //Метод executeQuery() может выполнять SQL запросы ( в аргументе переменная SQL запроса)
            ResultSet resultSet = statement.executeQuery(SQL);
            //ResultSet инкапсулирует результат выполнения запроса к БД

            while(resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
            //INSERT INTO Person VALUES (1, 'Tom', 18, 'tom@mail.ru')
            statement.executeUpdate(SQL);
            //Метод executeUpdate () позволяет менять данные в БД, при этом ничего не возвращает
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}