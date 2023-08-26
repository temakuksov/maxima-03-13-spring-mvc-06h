package ru.maxima.springwebmvc.util;


import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maxima.springwebmvc.dao.PersonDAO;
import ru.maxima.springwebmvc.entity.Person;


@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;


    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(@Nonnull Class<?> clazz) {
        return Person.class.equals(clazz);
    }




    @Override
    public void validate(@Nonnull Object target,@Nonnull Errors errors) {
        Person person = (Person) target;
        // посмотреть, есть ли в базе данных человек с такой же почтой и с id отличным от текущего

      /*  if (personDAO.show(person.getEmail()).isPresent()) {
            Person founded = (personDAO.show(person.getEmail())).get();
            if (founded.getId() != person.getId()) {
                errors.rejectValue("email", "", "Эта почта уже используется!");
            }
        }*/
    }
}
