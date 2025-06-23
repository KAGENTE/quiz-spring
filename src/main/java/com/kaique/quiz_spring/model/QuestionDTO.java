package com.kaique.quiz_spring.model;


import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class QuestionDTO {

    public QuestionDTO(Question q){
        this.id = q.getId();
        this.body = q.getBody();
        this.answ = q.getAnsw();
    }
   
    private int id;
    private String body;
    private ArrayList<String> answ;
    private byte rightAnsw;
    private byte option;

}
