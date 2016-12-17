package com.websystique.springsecurity.service;

import com.websystique.springsecurity.mapper.DataBaseMapper;
import com.websystique.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    DataBaseMapper dataMapper;
    
    @Override
    public User findByLogin(String login) {
        return dataMapper.findUserByLogin(login);
    }
}
