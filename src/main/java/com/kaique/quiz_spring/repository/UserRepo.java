package com.kaique.quiz_spring.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kaique.quiz_spring.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

}
