package com.kaique.quiz_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.quiz_spring.model.Question;
import com.kaique.quiz_spring.model.QuestionDTO;
import com.kaique.quiz_spring.service.QuestionService;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    QuestionService service;

    @PostMapping("save_question")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> saveQuestion(@RequestBody QuestionDTO dto) {
        return new ResponseEntity<>(service.SaveQuestion(dto),HttpStatus.ACCEPTED);
    }
    /*recebe um jason com formato
    {
        "body": "Qual é a capital da espanha?",
        "answ": [
        "São Paulo",
        "madrid",
        "Brasília",
        "Salvador"
        ],
        "rightAnsw": 2 
    }
        rightAnsw é o index da resposta correta
     */

    
    @DeleteMapping("question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        return new ResponseEntity<>(service.deleteQuestion(id),HttpStatus.OK);
    }
    /*
     * apaga a questão via ID
     */
    
    
    @PutMapping("question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody QuestionDTO dto) {
        return new ResponseEntity<>(service.updateQuestion(id, dto),HttpStatus.OK);
    }
    /*
     * edita a questão recebe um json e o id da questão a ser alterada
     * {
        "body": "Qual é a capital da noruega?",
        "answ": [
        "São Paulo",
        "madrid",
        "Brasília",
        "Salvador"
        ],
        "rightAnsw": 2
       }
     */
}
