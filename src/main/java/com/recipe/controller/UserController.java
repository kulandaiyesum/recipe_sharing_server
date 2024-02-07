package com.recipe.controller;

import com.recipe.model.User;
import com.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) throws Exception {
        return userService.updateUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) throws Exception {
        userService.deleteUser(userId);
    }

    @GetMapping("api/user/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return user;
    }

}
