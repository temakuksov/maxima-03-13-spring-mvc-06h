package ru.maxima.springwebmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.springwebmvc.entity.Person;

import java.util.List;


@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    // перегрузил метод show еще и через емайл
 /*   @Transactional
    public Optional<Person> show(String email1) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Person p where p.email= :email");
        return query.setParameter("email","email1").list();

        //return sessionFactory.getCurrentSession().get(Person.class, );
        //return jdbcTemplate.query("SELECT * FROM Person WHERE email=?",
        //        BeanPropertyRowMapper.newInstance(Person.class), email).stream().findAny();
        //           return Optional.of(jdbcTemplate.query("SELECT * FROM Person WHERE email=?",
        //                     BeanPropertyRowMapper.newInstance(Person.class), email));
    }*/

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person personBeUpdated = session.get(Person.class, id);

        personBeUpdated.setName(person.getName());
        personBeUpdated.setSurname(person.getSurname());
        personBeUpdated.setAge(person.getAge());
        personBeUpdated.setEmail(person.getEmail());
        session.persist(personBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    @Transactional
    public List<Person> findByName(String keyword) {
       Session session = sessionFactory.getCurrentSession();
       String query = "FROM Person p where p.name like '%".concat(keyword).concat("%'");
       return session.createQuery(query, Person.class).getResultList();
    }

    @Transactional
    public List<Person> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Person p where p.id=".concat(String.valueOf(id));
        return session.createQuery(query, Person.class).getResultList();
    }

}
