package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;
import org.springframework.stereotype.Service;


public interface UserService {		
    User findByLogin(String login);      
}