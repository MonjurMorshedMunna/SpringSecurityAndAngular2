package com.example.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by monju on 06-Jan-17.
 */
@Transactional
public abstract class JpaCRUDRepository <T, I> implements CRUDRepository {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private Environment env;

    Session session = entityManager.unwrap( Session.class );
    SessionImplementor sessionImplementor = entityManager.unwrap( SessionImplementor.class );

    SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap( SessionFactory.class );

    private int batchSize = Integer.parseInt(env.getProperty("hibernate.jdbc.batch_size"));


    @Override
    public <T> void create(T o){
        entityManager.persist(o);
    }

    private <T> void createOrUpdate(T o){
        session.saveOrUpdate(o);
    }

    @Override
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

    @Override
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

    @Override
    public <T> void delete(T o){
        if(entityManager.contains(o)){
            entityManager.remove(o);
        }
        else{
            entityManager.remove(entityManager.merge(o));
        }
    }

    @Override
    public <T> void update(T o){
        entityManager.merge(o);
    }


}
