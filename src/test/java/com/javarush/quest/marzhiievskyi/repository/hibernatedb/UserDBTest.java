package com.javarush.quest.marzhiievskyi.repository.hibernatedb;

import com.javarush.quest.marzhiievskyi.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserDBTest {
    Session session;
    SessionFactory sessionFactory;
    Transaction transaction;


    @BeforeEach
    void setUp() {
        sessionFactory = HibernateConnection.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void setDown() {
        transaction.rollback();
        session.close();
        sessionFactory.close();
    }


    @Test
    void find() {

    }

    @Test
    void getAll() {
        List<User> fromUser = session.createQuery("from User", User.class).list();
        fromUser.forEach(System.out::println);
    }

    @Test
    void get() {
        System.out.println(session.get(User.class, 1L));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}