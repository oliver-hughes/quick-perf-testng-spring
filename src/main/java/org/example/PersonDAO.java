package org.example;

import org.example.entity.Person;
import org.hibernate.SessionFactory;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PersonDAO {

    private SessionFactory sessionFactory;

    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> getPersonList() {
        TypedQuery<Person> fromPerson = sessionFactory.getCurrentSession().createQuery("FROM Person", Person.class);
        return fromPerson.getResultList();

    }

}
