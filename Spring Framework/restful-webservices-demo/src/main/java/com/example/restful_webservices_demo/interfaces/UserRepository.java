package com.example.restful_webservices_demo.interfaces;

import com.example.restful_webservices_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
