package com.project.project.dao;

import com.project.project.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();


    void delete(Long id);

    void createUser(User user);

    User getUserByCredentials(User user);
}
