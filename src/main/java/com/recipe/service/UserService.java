package com.recipe.service;

import com.recipe.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId) throws Exception;

    public User createUser(User user) throws Exception;

    public List<User> getAllUsers();

    public User updateUser(User user) throws Exception;

    public void deleteUser(Long userId) throws Exception;


    public User findUserByJwt(String jwt) throws Exception;
}
