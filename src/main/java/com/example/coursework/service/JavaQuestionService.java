package com.example.coursework.service;

import com.example.coursework.entity.Question;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = ConcurrentHashMap.newKeySet();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        throw new IllegalArgumentException("Question not found: " + question.getQuestion());
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NoSuchElementException("No questions available");
        }
        
        int randomIndex = random.nextInt(questions.size());
        Iterator<Question> iterator = questions.iterator();
        
        for (int i = 0; i < randomIndex; i++) {
            iterator.next();
        }
        
        return iterator.next();
    }
}
