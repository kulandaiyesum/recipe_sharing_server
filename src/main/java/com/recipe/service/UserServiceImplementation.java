package com.recipe.service;


import com.recipe.config.JwtProvider;
import com.recipe.model.User;
import com.recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("User not found with Id" + userId);
    }

    @Override
    public User createUser(User user) throws Exception {
        User isUserExist = userRepository.findByEmail(user.getEmail());
        if (isUserExist != null) {
            throw new Exception("User not found with email" + user.getEmail());
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) throws Exception {
        User updateUser = findUserById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        findUserById(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email= jwtProvider.getEmailFromJwt(jwt);
        if(email == null) {
            throw new Exception("provide valid jwt token");
        }
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("User not found with email"+ email);
        }
        return user;
    }
}
