package com.userService.service;

import com.userService.entity.User;

import java.util.List;

public interface UserService {

    //create user
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get user with id
    User getUserById(String userId);

    //delete User with userId
    User deleteUser(String userId);

    //update User
    User updateUser(String userId, User user);
}
