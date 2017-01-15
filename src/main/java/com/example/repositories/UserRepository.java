package com.example.repositories;

import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by monju on 15-Jan-17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUserName(String pUserName);
}
