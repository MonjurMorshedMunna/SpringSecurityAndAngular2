package com.example.repositories;

import com.example.models.User;
import com.example.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by monju on 15-Jan-17.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole getByUser(final User pUser);
}
