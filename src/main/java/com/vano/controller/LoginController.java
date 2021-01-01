package com.vano.controller;


import com.vano.config.AuthManager;
import com.vano.config.JwtTokenProvider;
import com.vano.config.UserPrincipal;
import com.vano.controller.requestPojo.ApiResponse;
import com.vano.controller.requestPojo.LoginRequest;
import com.vano.entity.User;
import com.vano.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserDetailsService userDetailservice;

    @Autowired
    UserService userService;

    @Autowired
    AuthManager authManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/status")//post and get
    public ResponseEntity<?> serverStatus() {
        return new ResponseEntity<>(new ApiResponse("Server is running.", ""), HttpStatus.OK);
    }

    @RequestMapping("/login/user")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMobile(), loginRequest.getPassword()), loginRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.generateToken(authentication);
            JSONObject obj = this.getUserResponse(token);

            if (obj == null) {
                throw new Exception("Error while generating Reponse");
            }

            return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
        } catch (Exception exception) {
            logger.info("Error in authenticateUser ",exception);
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), ""));
        }
    }

    private JSONObject getUserResponse(String token) {
        try {
            User user = userService.getUserDetailById(getUserId());
            HashMap<String,String> response = new HashMap<String,String>();
            response.put("user_id", ""+getUserId());
            response.put("email", user.getEmail());
            response.put("name", user.getName());
            response.put("mobile", user.getMobile());

            JSONObject obj = new JSONObject();

            obj.put("user_profile_details",response);
            obj.put("token", token);

            return obj;
        } catch (Exception e) {
            logger.info("Error in getUserResponse ", e);
        }
        return null;
    }


    private long getUserId() {
        logger.info("user id vaildating. " + SecurityContextHolder.getContext().getAuthentication());
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("(LoginController)user id is " + userPrincipal.getId());
        return userPrincipal.getId();
    }


}
