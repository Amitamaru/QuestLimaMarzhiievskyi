package com.javarush.quest.marzhiievskyi.repository.hibernatedb;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.repository.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.stream.Stream;

public class UserDB implements Repository<User> {

    @Override
    public Stream<User> find(User pattern) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where User.login = :patternLogin", User.class);
            query.setParameter("patternLogin", pattern.getLogin());
            return (Stream<User>) query.uniqueResult();
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

    public static void main(String[] args) {
        User demoUser = User.builder()
                .id(10L)
                .build();

        UserDB repo = new UserDB();
        repo.getAll().forEach(System.out::println);
    }

}
