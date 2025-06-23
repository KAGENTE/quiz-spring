package com.kaique.quiz_spring.model;


import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Questions")
public class Question {

    public Question(QuestionDTO dto){
        this.body = dto.getBody();
        this.answ = dto.getAnsw();
        this.rightAnsw = dto.getRightAnsw();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String body;
    @Column(nullable = false)
    private ArrayList<String> answ;
    @Column(nullable = false)
    private byte rightAnsw;
    



}
