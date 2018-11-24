package com.university.repository.impl;

import com.university.repository.abstracts.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public abstract class AbstractDao<PK extends Serializable, T> implements GenericDao<PK, T> {
    @Autowired
    protected MongoTemplate mongoTemplate;

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        String genericClassName = persistentClass.toGenericString();
        String className = genericClassName.substring(genericClassName.lastIndexOf('.') + 1);
    }

    public T getByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, persistentClass);
    }

    public void persist(T entity) {
        mongoTemplate.insert(entity);
    }

    public void update(T entity) {
        mongoTemplate.save(entity);
    }

    public List<T> getAll() {
        return mongoTemplate.findAll(persistentClass);
    }

    public T getByKey(PK pk) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(pk));
        return mongoTemplate.findOne(query, persistentClass);
    }

    public void delete(PK pk) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(pk));
        mongoTemplate.remove(query, persistentClass);
    }

    public void delete(T entity) {
        mongoTemplate.remove(entity);
    }
}
