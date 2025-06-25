package com.kaique.quiz_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.quiz_spring.model.Question;
import com.kaique.quiz_spring.model.QuestionDTO;
import com.kaique.quiz_spring.repository.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;

    public QuestionDTO GetQuestion(int id) {
        return new QuestionDTO(repo.findById(id).get());
    }

    public String checkAnsw(QuestionDTO dto) {
        Question q = repo.findById(dto.getId()).get();
        if(q.getRightAnsw() == dto.getOption()){
            return "Acertou";
        }else{return "Errou";}

    }

    public Question SaveQuestion(QuestionDTO dto){
        return repo.save(new Question(dto));
    }


    public String deleteQuestion(int id) {
        if(repo.findById(id).isPresent()){
            repo.deleteById(id);
            return "Questão apagada com sucesso";
        }else{
            throw new IllegalArgumentException("Questão não encontrada");
        }
    }

    public Question updateQuestion(int id, QuestionDTO dto) {
        Question q = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Pergunta não encontrada"));
        q.setAnsw(dto.getAnsw());
        q.setBody(dto.getBody());
        q.setRightAnsw(dto.getRightAnsw());
        return repo.save(q);
    }
}
