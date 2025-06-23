package com.kaique.quiz_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaique.quiz_spring.model.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer> {



}
