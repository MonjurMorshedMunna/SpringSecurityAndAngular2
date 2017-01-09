package com.example.repositories;

import com.example.models.User;

import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;
import java.util.List;

/**
 * Created by monju on 06-Jan-17.
 */
@Repository
public class UserRepository extends JpaCRUDRepository<User, Integer>{
    public User getUser(final String pUserName){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("userName"),pUserName));
        Query query = entityManager.createQuery(criteria);
        User mUser = (User)query.getSingleResult();
        return mUser;
    }

    public boolean containsUser(final User pUser){
        return getSession().contains(pUser);
    }
    
    public Number getUserDataSize(){
    	return (Number) getSession().createCriteria(User.class).setProjection(Projections.rowCount()).uniqueResult();
    }


}
