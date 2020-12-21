package ru.kinghp.crudapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kinghp.crudapp.models.Person;
import ru.kinghp.crudapp.utils.HibernateSessionFactoryUtil;

import java.util.Collection;
import java.util.List;

//Data Access Object взаимодействует с бд
@Component
public class PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> index(){
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        return (List<Person>) session.createQuery("from Person").list();
        return sessionFactory.openSession()
                .createQuery("from Person").list();
    }

    public Person show(int id){
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        return session.get(Person.class, id);
        return sessionFactory.openSession()
                .get(Person.class, id);
    }

    public void save(Person person){
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(person);
        tx1.commit();
        session.close();
    }

    public void update(int id, Person updatedPerson){

//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(updatedPerson);
        tx1.commit();
        session.close();


    }

    public void delete(int id){
        Person personToBeDeleted = show(id);
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(personToBeDeleted);
        tx1.commit();
        session.close();
    }
}
