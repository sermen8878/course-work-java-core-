package com.example.coursework.service;

import com.example.coursework.entity.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = javaQuestionService.getAll();
        
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be positive");
        }
        
        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "Requested " + amount + " questions, but only " + allQuestions.size() + " available"
            );
        }

        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            Question randomQuestion = javaQuestionService.getRandomQuestion();
            randomQuestions.add(randomQuestion);
        }

        return randomQuestions;
    }
}
