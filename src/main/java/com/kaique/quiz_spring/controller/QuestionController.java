package com.kaique.quiz_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kaique.quiz_spring.model.QuestionDTO;
import com.kaique.quiz_spring.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/quiz")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("question/{id}")
    public ResponseEntity<QuestionDTO> GetQuestion(@PathVariable int id){
        return new ResponseEntity<>(service.GetQuestion(id), HttpStatus.OK); 
    }
    
    @PostMapping("answ/{option}")
    public ResponseEntity<String> chooseOption(@RequestBody QuestionDTO dto) {
        return new ResponseEntity<>(service.checkAnsw(dto),HttpStatus.ACCEPTED);
    }


    
}
