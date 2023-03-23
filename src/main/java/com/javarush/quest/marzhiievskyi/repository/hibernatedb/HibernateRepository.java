package com.javarush.quest.marzhiievskyi.repository.hibernatedb;

import java.util.Collection;

public interface HibernateRepository<T> {

    T find(T pattern);
    Collection<T> getAll();
    T get(Long id);
    void create(T entity);
    void update(T entity);
    void delete(T entity);

}
