package com.university.repository.abstracts;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {

    T getByName(String name);

    void persist(T entity);

    void update(T entity);

    List<T> getAll();

    T getByKey(PK pk);

    void deleteByKey(PK pk);

    void deleteByKey(T entity);
}
