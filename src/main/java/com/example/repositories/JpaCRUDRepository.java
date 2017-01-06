package com.example.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by monju on 06-Jan-17.
 */
@Repository
@Transactional
public abstract class JpaCRUDRepository <T, I>{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Environment env;

    Session session = entityManager.unwrap( Session.class );
    SessionImplementor sessionImplementor = entityManager.unwrap( SessionImplementor.class );

    SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap( SessionFactory.class );

    private int batchSize = Integer.parseInt(env.getProperty("hibernate.jdbc.batch_size"));


    public <T> void create(T o){
        entityManager.persist(o);
    }

    private <T> void createOrUpdate(T o){
        session.saveOrUpdate(o);
    }

    public <T> void createOrUpdate(List<T> o){
        int i=0;
        for(T t: o){
            session.saveOrUpdate(t);
            ++i;
            if(i% batchSize==0){
                session.flush();
                session.clear();
            }
        }
    }

    public <T> void delete(List<T> o){
        int i=0;
        for(T t: o){
            session.delete(o);
            ++i;
            if(i%batchSize==0){
                session.flush();
                session.clear();
            }
        }
    }

    public <T> void delete(T t){
        if(entityManager.contains(t)){
            entityManager.remove(t);
        }
        else{
            entityManager.remove(entityManager.merge(t));
        }
    }

    public <T> void update(T t){
        entityManager.merge(t);
    }


}
