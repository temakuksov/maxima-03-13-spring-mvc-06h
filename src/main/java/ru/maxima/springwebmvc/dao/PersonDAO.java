package ru.maxima.springwebmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springwebmvc.entity.Person;
import java.util.List;
import java.util.Optional;


/**
 * @author AramaJava 26.07.2023
 */

    /*
        Добавить на главную страницу приложения возможность поиска по имени.

        Отдельное поле, куда нужно будет написать искомое имя
         + кнопку для поиска и выдавать всех пользователей,
        у которых имя начинается на искомый стринг

        и возвращать пользователю новую страницу сo всеми людьми,
        где будет сверху надпись "Вы искали людей с именем ИСКОМОЕ_ИМЯ"
        и список всех людей, у которых имя начинается на искомый стринг.

        Метод контроллера с поиском по БД должен быть написан
        с вызовом ДАО класса, который будет использовать JDBCTemplate,
        необходимо погуглить, как искать в БД строки с колонками,
        начинающиеся на искомый стринг.

*/


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        String sql = "select * from person";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE id=?",
                BeanPropertyRowMapper.newInstance(Person.class), id);
    }
    // перегрузил метод show еще и через емайл
    public Optional<Person> show(String email) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?",
               BeanPropertyRowMapper.newInstance(Person.class), email).stream().findAny();
//           return Optional.of(jdbcTemplate.query("SELECT * FROM Person WHERE email=?",
 //                     BeanPropertyRowMapper.newInstance(Person.class), email));

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, surname, age, email, address) VALUES (?, ?, ?, ?, ?)",
                person.getName(),
                person.getSurname(),
                person.getAge(),
                person.getEmail(),
                person.getAddress());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, surname=?, age=?, email=?, address=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getSurname(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                updatedPerson.getAddress(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from person WHERE id=?", id);
    }

    public  List<Person> findByName(String keyword) {
        String sql = "select * from person p where p.name like ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class),keyword+'%');
    }

}
