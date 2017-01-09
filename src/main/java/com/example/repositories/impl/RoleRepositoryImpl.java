package com.example.repositories.impl;

import com.example.models.Role;
import com.example.repositories.RoleRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by monju on 06-Jan-17.
 */
@Repository
public class RoleRepositoryImpl extends JpaCRUDRepository<Role, Integer> implements RoleRepository {

}
