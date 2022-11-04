package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.UserNotFoundException;

import java.util.List;

public interface UserService {


    User saveUser(User user);

    User findByEmailAndPassword(String email,String password) throws UserNotFoundException;

    List<User> getAllUsers();
    String getPassword(String email);
}
