package com.example.repositories.impl;

import com.example.models.User;

import com.example.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by monju on 06-Jan-17.
 */
@Repository
public class UserRepositoryImpl extends JpaCRUDRepository<User, Integer> implements UserRepository {
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
    


}
