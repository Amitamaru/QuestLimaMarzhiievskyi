package com.javarush.quest.marzhiievskyi.repository.hibernatedb;

import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.entity.GameSession;
import com.javarush.quest.marzhiievskyi.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
    private static HibernateConnection instance;
    private final SessionFactory sessionFactory;

    public HibernateConnection() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Answer.class)
                .addAnnotatedClass(GameSession.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new HibernateConnection();
        }
        return instance.sessionFactory;
    }
}
