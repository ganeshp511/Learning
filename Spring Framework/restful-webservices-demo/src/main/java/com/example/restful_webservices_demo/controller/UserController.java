package com.example.restful_webservices_demo.controller;

import com.example.restful_webservices_demo.interfaces.UserServiceInterface;
import com.example.restful_webservices_demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceInterface userService;
    @PostMapping("/user")
    public User addUserDetails(@Valid @RequestBody User user){
        logger.info("Received User: {}", user);
        return userService.addUser(user);
    }

    @GetMapping("/user")
    public List<User> getAllUserDetails(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user=userService.getUserById(id).orElse(null);
        if(user !=null){
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
