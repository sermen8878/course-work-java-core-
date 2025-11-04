package com.example.coursework.service;

import com.example.coursework.entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void testAddQuestionWithParams() {
        Question result = javaQuestionService.add("What is Java?", "Programming language");
        
        assertEquals("What is Java?", result.getQuestion());
        assertEquals("Programming language", result.getAnswer());
        assertTrue(javaQuestionService.getAll().contains(result));
    }

    @Test
    void testAddQuestionObject() {
        Question question = new Question("What is OOP?", "Object-Oriented Programming");
        Question result = javaQuestionService.add(question);
        
        assertEquals(question, result);
        assertTrue(javaQuestionService.getAll().contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = javaQuestionService.add("What is Java?", "Programming language");
        
        Question removed = javaQuestionService.remove(question);
        
        assertEquals(question, removed);
        assertFalse(javaQuestionService.getAll().contains(question));
    }

    @Test
    void testRemoveNonExistentQuestion() {
        Question question = new Question("Non-existent", "Question");
        
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.remove(question));
    }

    @Test
    void testGetAllQuestions() {
        Question q1 = javaQuestionService.add("Q1", "A1");
        Question q2 = javaQuestionService.add("Q2", "A2");
        
        Collection<Question> allQuestions = javaQuestionService.getAll();
        
        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(q1));
        assertTrue(allQuestions.contains(q2));
    }

    @Test
    void testGetRandomQuestion() {
        javaQuestionService.add("Q1", "A1");
        javaQuestionService.add("Q2", "A2");
        javaQuestionService.add("Q3", "A3");
        
        Question random = javaQuestionService.getRandomQuestion();
        
        assertNotNull(random);
        assertTrue(random.getQuestion().startsWith("Q"));
    }

    @Test
    void testGetRandomQuestionFromEmptySet() {
        assertThrows(NoSuchElementException.class, () -> javaQuestionService.getRandomQuestion());
    }

    @Test
    void testQuestionUniqueness() {
        Question q1 = javaQuestionService.add("Same", "Answer");
        Question q2 = javaQuestionService.add("Same", "Answer");
        
        Collection<Question> allQuestions = javaQuestionService.getAll();
        
        assertEquals(1, allQuestions.size());
    }
}
