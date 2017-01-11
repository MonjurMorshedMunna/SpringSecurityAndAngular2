package com.example.repositories;

import com.example.models.User;
import com.example.models.UserRole;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by monju on 09-Jan-17.
 */
public interface UserRoleRepository  {
    UserRole getUser(final User pUser);
}
