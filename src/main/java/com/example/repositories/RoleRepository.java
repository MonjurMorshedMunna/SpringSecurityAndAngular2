package com.example.repositories;

import com.example.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by monju on 15-Jan-17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
