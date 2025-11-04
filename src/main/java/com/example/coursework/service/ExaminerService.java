package com.example.coursework.service;

import com.example.coursework.entity.Question;
import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
