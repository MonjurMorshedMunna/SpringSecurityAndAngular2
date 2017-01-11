package com.example.repositories.impl;

import com.example.models.User;
import com.example.models.UserRole;
import com.example.repositories.UserRoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by monju on 06-Jan-17.
 */
@Repository
public class UserRoleRepositoryImpl
        extends JpaCRUDRepository<UserRole, Integer>
        implements UserRoleRepository{

    @Override
    public UserRole getUser(User pUser) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserRole> criteria = builder.createQuery(UserRole.class);
        Root<UserRole> root = criteria.from(UserRole.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("user"), pUser));
        Query query = entityManager.createQuery(criteria);
        UserRole userRole = (UserRole) query.getSingleResult();
        return userRole;
    }


}
