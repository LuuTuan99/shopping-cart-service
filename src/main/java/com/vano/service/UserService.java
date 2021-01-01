package com.vano.service;

import com.vano.entity.User;
import com.vano.repository.UserRepository;
import com.vano.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService implements UserServiceImpl {


    @Autowired
    UserRepository userRepository;

    @Override
    public User findByMobile(String mobile) throws Exception {
        return userRepository.findByMobile(mobile).orElseThrow(()->new Exception("User Not found.."));
    }

    @Override
    public User getUserDetailById(long userId) throws Exception {

        return userRepository.findById(userId).orElseThrow(()->new Exception("User Not found.."));
    }

    @Override
    public User signUpUser(HashMap<String, String> signupRequest) throws Exception {
        return null;
    }
}
