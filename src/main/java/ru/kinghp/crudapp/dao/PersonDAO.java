package ru.kinghp.crudapp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.kinghp.crudapp.models.Person;
import ru.kinghp.crudapp.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

//Data Access Object взаимодействует с бд
@Component
public class PersonDAO {
    public static int PEOPLE_COUNT;
//    private List<Person> people;

    {
//        people = new ArrayList<>();
//
//        people.add(new Person(++PEOPLE_COUNT, "Tom"));
//        people.add(new Person(++PEOPLE_COUNT, "Sid"));
//        people.add(new Person(++PEOPLE_COUNT, "Jimmy"));
//        people.add(new Person(++PEOPLE_COUNT, "Kim"));

    }

    public List<Person> index(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return (List<Person>) session.createQuery("from Person").list();
//        return people;
    }

    public Person show(int id){
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.get(Person.class, id);
    }

    public void save(Person person){
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(person);
        tx1.commit();
        session.close();
    }

    public void update(int id, Person updatedPerson){
//        Person personToBeUpdated = show(id);
//
//        if (personToBeUpdated == null){
//            return;
//        }
//
//        personToBeUpdated.setName(updatedPerson.getName());

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(updatedPerson);
        tx1.commit();
        session.close();


    }

    public void delete(int id){
//        people.removeIf(person -> person.getId() == id);
        Person personToBeDeleted = show(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(personToBeDeleted);
        tx1.commit();
        session.close();
    }
}
