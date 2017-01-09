package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;
import java.util.Date;



public interface UserService {		
    User findByLogin(String login);  
    void updateAccessToken(User user);
}