package com.medicine.medicine.service;

import com.medicine.medicine.entity.ExampleEntity;
import com.medicine.medicine.entity.QuizEntity;
import com.medicine.medicine.repository.ExampleRepository;
import com.medicine.medicine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ExampleRepository exampleRepository;

    public QuizEntity getQuiz(Long id) {
        Optional<QuizEntity> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }

    public ExampleEntity getExample(Long quizId) {
        QuizEntity quiz = getQuiz(quizId);
        if (quiz != null) {
            return exampleRepository.findByQuiz(quiz);
        }
        return null;
    }

    public QuizEntity getNextQuiz(Long lastId) {
        Optional<QuizEntity> quiz = quizRepository.findFirstByIdGreaterThanOrderByIdAsc(lastId);
        return quiz.orElse(null);
    }
}
