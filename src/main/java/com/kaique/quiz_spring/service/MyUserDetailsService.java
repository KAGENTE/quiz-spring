package com.kaique.quiz_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.kaique.quiz_spring.model.User;
import com.kaique.quiz_spring.model.UserPrincipal;
import com.kaique.quiz_spring.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByName(username).get();
        if(user == null){
            throw new UsernameNotFoundException("User not found: "+username);
        }
        return new UserPrincipal(user);
    }



}
