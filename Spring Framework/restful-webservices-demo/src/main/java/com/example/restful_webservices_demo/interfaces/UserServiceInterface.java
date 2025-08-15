package com.example.restful_webservices_demo.interfaces;

import com.example.restful_webservices_demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    public User addUser(User user);
    public List<User> getAllUser();
    public Optional<User> getUserById(int id);
    public User updateUserDetails(int id, User user);
}

