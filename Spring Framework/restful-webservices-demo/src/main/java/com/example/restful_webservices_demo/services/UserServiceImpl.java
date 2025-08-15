package com.example.restful_webservices_demo.services;

import com.example.restful_webservices_demo.interfaces.UserRepository;
import com.example.restful_webservices_demo.interfaces.UserServiceInterface;
import com.example.restful_webservices_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUserDetails(int id, User newUser) {
        User userData=userRepository.findById(id).orElse(null);
        if(userData!=null){
            return userRepository.save(newUser);
        }else{
         throw new RuntimeException("User not found with id"+id);
        }
    }
}
