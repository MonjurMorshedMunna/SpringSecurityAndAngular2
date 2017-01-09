package com.example.repositories.impl;

import com.example.models.UserRole;
import com.example.repositories.UserRoleRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by monju on 06-Jan-17.
 */
@Repository
public class UserRoleRepositoryImpl
        extends JpaCRUDRepository<UserRole, Integer>
        implements UserRoleRepository{

}
