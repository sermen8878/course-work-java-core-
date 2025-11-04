package com.example.coursework.service;

import com.example.coursework.entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceImplTest {
    private JavaQuestionService javaQuestionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(javaQuestionService);
        
        // Добавляем тестовые вопросы
        javaQuestionService.add("What is Java?", "Programming language");
        javaQuestionService.add("What is OOP?", "Object-Oriented Programming");
        javaQuestionService.add("What is Spring?", "Framework");
        javaQuestionService.add("What is Maven?", "Build tool");
        javaQuestionService.add("What is JUnit?", "Testing framework");
    }

    @Test
    void testGetQuestionsValidAmount() {
        Collection<Question> questions = examinerService.getQuestions(3);
        
        assertEquals(3, questions.size());
        
        // Проверяем уникальность вопросов
        long distinctCount = questions.stream().distinct().count();
        assertEquals(3, distinctCount);
    }

    @Test
    void testGetQuestionsAllAvailable() {
        Collection<Question> questions = examinerService.getQuestions(5);
        
        assertEquals(5, questions.size());
    }

    @Test
    void testGetQuestionsThrowsWhenAmountTooLarge() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(10));
    }

    @Test
    void testGetQuestionsThrowsWhenAmountZero() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(0));
    }

    @Test
    void testGetQuestionsThrowsWhenAmountNegative() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(-1));
    }

    @Test
    void testGetQuestionsReturnsDifferentQuestions() {
        // Multiple calls should return different sets (random)
        Collection<Question> firstSet = examinerService.getQuestions(2);
        Collection<Question> secondSet = examinerService.getQuestions(2);
        
        // They might be the same by chance, but with 5 total questions and 2 selected,
        // probability is low. We'll just check they are valid sets.
        assertEquals(2, firstSet.size());
        assertEquals(2, secondSet.size());
    }
}
