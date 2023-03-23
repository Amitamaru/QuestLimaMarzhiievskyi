package com.javarush.quest.marzhiievskyi.repository.hibernatedb;

import com.javarush.quest.marzhiievskyi.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;


public class UserDB implements HibernateRepository<User> {

    @Override
    public User find(User pattern) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User u where u.login = :patternLogin", User.class);
            query.setParameter("patternLogin", pattern.getLogin());
            return query.uniqueResult();
        }
    }

    @Override
    public Collection<User> getAll() {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public User get(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
           return session.get(User.class, id);
        }
    }

    @Override
    public void create(User userToCreate) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(userToCreate);
            transaction.commit();
        }
    }

    @Override
    public void update(User userToUpdate) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(userToUpdate);
            transaction.commit();
        }
    }

    @Override
    public void delete(User userToDelete) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(userToDelete);
            transaction.commit();
        }

    }

}
