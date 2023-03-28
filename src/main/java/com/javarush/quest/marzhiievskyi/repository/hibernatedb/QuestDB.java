package com.javarush.quest.marzhiievskyi.repository.hibernatedb;

import com.javarush.quest.marzhiievskyi.entity.Quest;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;

public class QuestDB implements HibernateRepository<Quest> {
    @Override
    public Quest find(Quest pattern) {
        return null;
    }//TODO find

    @Override
    public Collection<Quest> getAll() {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
           return session.createQuery("from Quest", Quest.class).list();
        }
    }

    @Override
    public Quest get(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            return session.get(Quest.class, id);
        }
    }

    @Override
    public void create(Quest questToCreate) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(questToCreate);
            transaction.commit();
        }
    }

    @Override
    public void update(Quest entity) {//TODO update

    }

    @Override
    public void delete(Quest entity) {//TODO delete

    }
}
