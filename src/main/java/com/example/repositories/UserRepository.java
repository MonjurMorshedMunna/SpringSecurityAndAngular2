package com.example.repositories;

import com.example.models.User;

/**
 * Created by monju on 09-Jan-17.
 */
public interface UserRepository  {
    User getUser(final String pUserName);

    boolean containsUser(final User pUser);

}
