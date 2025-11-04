package com.example.coursework.controller;

import com.example.coursework.entity.Question;
import com.example.coursework.service.JavaQuestionService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return javaQuestionService.getAll();
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestParam String question, 
                               @RequestParam String answer) {
        return javaQuestionService.add(question, answer);
    }

    @DeleteMapping("/remove")
    public Question removeQuestion(@RequestParam String question, 
                                  @RequestParam String answer) {
        Question questionToRemove = new Question(question, answer);
        return javaQuestionService.remove(questionToRemove);
    }
}
