package com.medicine.medicine.service;

import com.medicine.medicine.entity.ExampleEntity;
import com.medicine.medicine.entity.QuizEntity;
import com.medicine.medicine.entity.SeenQuizEntity;
import com.medicine.medicine.entity.UserEntity;
import com.medicine.medicine.repository.ExampleRepository;
import com.medicine.medicine.repository.QuizRepository;
import com.medicine.medicine.repository.SeenQuizRepository;
import com.medicine.medicine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ExampleRepository exampleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeenQuizRepository seenQuizRepository;

    public QuizEntity getRandomQuizExcludingSeen(UserEntity user) {
        List<Long> seenQuizIds = seenQuizRepository.findQuizIdsByUserId(user.getId());
        if (seenQuizIds == null || seenQuizIds.isEmpty()) {
            //System.out.println("Fetching a random quiz (no excluded IDs)");
            Optional<QuizEntity> quiz = quizRepository.findRandomQuiz();
            if (quiz.isPresent()) {
                //System.out.println("Found quiz with ID: " + quiz.get().getId());
            } else {
                //System.out.println("No quizzes found.");
            }
            return quiz.orElse(null);
        } else {
            //System.out.println("Fetching a random quiz excluding IDs: " + seenQuizIds);
            Optional<QuizEntity> quiz = quizRepository.findRandomQuizExcludingIds(seenQuizIds);
            if (quiz.isPresent()) {
                //System.out.println("Found quiz with ID: " + quiz.get().getId());
            } else {
                //System.out.println("No quizzes found.");
            }
            return quiz.orElse(null);
        }
    }

    public void markQuizAsSeen(UserEntity user, QuizEntity quiz) {
        SeenQuizEntity seenQuiz = new SeenQuizEntity(user, quiz);
        seenQuizRepository.save(seenQuiz);
    }

    public List<Long> getSeenQuizIds(UserEntity user) {
        List<Long> seenQuizIds = seenQuizRepository.findQuizIdsByUserId(user.getId());
        if (seenQuizIds == null) {
            seenQuizIds = new ArrayList<>();
        }
        return seenQuizIds;
    }

    public ExampleEntity getExample(Long quizId) {
        //System.out.println("Fetching example for quiz ID: " + quizId);
        QuizEntity quiz = getQuiz(quizId);
        if (quiz != null) {
            return exampleRepository.findByQuiz(quiz);
        }
        return null;
    }

    public UserEntity getUserByLoginid(String loginid) {
        //System.out.println("Fetching user with loginid: " + loginid);
        Optional<UserEntity> optionalUser = userRepository.findByLoginid(loginid);
        UserEntity userEntity = optionalUser.get();
        return userEntity;
    }

    private QuizEntity getQuiz(Long id) {
        Optional<QuizEntity> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }
}
