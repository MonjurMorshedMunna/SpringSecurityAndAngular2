package com.example.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by monju on 06-Jan-17.
 */

@Transactional
public abstract class JpaCRUDRepository <T, I>  {

    @PersistenceContext
    public EntityManager entityManager;

/*
    Session session = entityManager.unwrap( Session.class );
    SessionImplementor sessionImplementor = entityManager.unwrap( SessionImplementor.class );

    SessionFactory sessionFactory = entityManager.getEntityManagerFactory().unwrap( SessionFactory.class );*/

   //Session session = entityManager.unwrap(Session.class);

    public Session getSession(){
        return  entityManager.unwrap( Session.class );
    }

    public SessionFactory getSessionFactory(){
        return entityManager.getEntityManagerFactory().unwrap( SessionFactory.class );
    }




    @Autowired
    private Environment env;



    private int batchSize = 20;


    
    public <T> void create(T o){
        entityManager.persist(o);
    }

    private <T> void createOrUpdate(T o){
        entityManager.merge(o);
    }

    
    public <T> void createOrUpdate(List<T> o){
        int i=0;
        for(T t: o){
            entityManager.merge(o);
            ++i;
            if(i% batchSize==0){
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    
    public <T> void delete(List<T> o){
        int i=0;
        for(T t: o){
            entityManager.remove(o);
            ++i;
            if(i%batchSize==0){
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    
    public <T> void delete(T o){
        if(entityManager.contains(o)){
            entityManager.remove(o);
        }
        else{
            entityManager.remove(o);
        }
    }

    
    public <T> void update(T o){
        entityManager.remove(o);
    }

    public Long getDataSize(T t){
        return (Long) getSession().createCriteria(t.getClass()).setProjection(Projections.rowCount()).uniqueResult();
    }

    public <T> void getParentClassInfo(T t){
        System.out.println(t.getClass());
    }


}
