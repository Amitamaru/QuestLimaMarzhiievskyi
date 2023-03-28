package com.javarush.quest.marzhiievskyi.repository.hibernatedb;

import com.javarush.quest.marzhiievskyi.entity.Quest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestDBTest {
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
        List<Quest> fromQuest = session.createQuery("from Quest", Quest.class).list();
        fromQuest.forEach(System.out::println);
    }

    @Test
    void get() {
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