package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.websystique.springsecurity.mapper.DBMapper;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    DBMapper dataMapper;
    
    @Override
    public User findByLogin(String login) {
        return dataMapper.findUserByLogin(login);
    }

    @Override
    public void updateAccessToken(User user) {
        dataMapper.updateAccessToken(user);
    }
    
}
