package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by monju on 10-Jan-17.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUser(s);
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(userRoleRepository
                        .getUser(user)
                        .getRole()
                        .getRoleName()));
    }
}
