package com.kaique.quiz_spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.kaique.quiz_spring.model.Role;
import com.kaique.quiz_spring.model.User;
import com.kaique.quiz_spring.model.UserDTO;
import com.kaique.quiz_spring.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public User saveNewUser(UserDTO userDTO){

        if(repo.findByName(userDTO.getName()).isPresent()){
            throw new IllegalArgumentException("Username ja esta em uso");
        }

        User u = new User();
        u.setName(userDTO.getName());
        u.setEmail(userDTO.getEmail());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setRole(Role.User);
        return repo.save(u);
    }



}
