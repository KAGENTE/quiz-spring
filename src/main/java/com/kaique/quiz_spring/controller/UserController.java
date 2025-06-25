package com.kaique.quiz_spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.quiz_spring.config.jwt.JwtService;
import com.kaique.quiz_spring.model.User;
import com.kaique.quiz_spring.model.UserDTO;
import com.kaique.quiz_spring.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> signUpUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(service.saveNewUser(userDTO), HttpStatus.ACCEPTED);
    }/*recebe um jason no formato
    {
        "name" : "exemplo",
        "email" : "exemplo@gmail.com",
        "password" : "12345"
    }
     */

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getName(), userDTO.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDTO);
        
        }else return "fail";
    }
    /*recebe um json no formato:
    {
        "name" : "exemplo",
        "password" : "12345"
    }
     */
    
}
