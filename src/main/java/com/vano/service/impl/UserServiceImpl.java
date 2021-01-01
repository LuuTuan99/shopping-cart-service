package com.vano.service.impl;

import com.vano.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface UserServiceImpl {

    User findByMobile(String mobile) throws Exception;
    User getUserDetailById(long userId) throws Exception;
    User signUpUser(HashMap<String,String> signupRequest) throws Exception;
}
