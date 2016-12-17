package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class AuthService {
    
    public static User getCurrentUser(UserService service)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) 
        {
            String login = ((UserDetails) principal).getUsername();
            User loginUser = service.findByLogin(login);
            return  loginUser;
        }
        return null;
    }
}
