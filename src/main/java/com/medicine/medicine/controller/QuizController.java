package com.medicine.medicine.controller;

import com.medicine.medicine.dto.ExampleDTO;
import com.medicine.medicine.dto.QuizResponseDTO;
import com.medicine.medicine.dto.ResponseDTO;
import com.medicine.medicine.dto.UserDTO;
import com.medicine.medicine.entity.ExampleEntity;
import com.medicine.medicine.entity.QuizEntity;
import com.medicine.medicine.entity.UserEntity;
import com.medicine.medicine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/main/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/{loginid}")
    public ResponseDTO getNextQuiz(@PathVariable String loginid) {
        try {
            //System.out.println("Received request for user with loginid: " + loginid);
            UserEntity user = quizService.getUserByLoginid(loginid);
            if (user == null) {
                //System.out.println("User not found with loginid: " + loginid);
                throw new RuntimeException("User not found"); // Throwing an exception to be handled by Spring
            }
            Integer count = user.getCount();
            //System.out.println("User count for loginid " + loginid + ": " + count);

            List<Long> seenQuizIds = quizService.getSeenQuizIds(user);
            if (seenQuizIds == null) {
                seenQuizIds = new ArrayList<>();
            }
            //System.out.println("Seen Quiz IDs: " + seenQuizIds);

            QuizEntity quiz = quizService.getRandomQuizExcludingSeen(user);
            if (quiz != null) {
                //System.out.println("Found quiz: " + quiz.getProblem());
                ExampleEntity example = quizService.getExample(quiz.getId());
                if (example != null) {
                    //System.out.println("Found example for quiz ID " + quiz.getId() + ": " + example.getAnswer());

                    // Mark the quiz as seen for the user
                    quizService.markQuizAsSeen(user, quiz);

                    UserDTO userDTO = new UserDTO(count);
                    ExampleDTO exampleDTO = new ExampleDTO(example.getAnswer(), example.getA(), example.getB(), example.getC());
                    QuizResponseDTO quizResponseDTO = new QuizResponseDTO(quiz.getId(), quiz.getCategory(), quiz.getProblem(), quiz.getExplanation(), exampleDTO);

                    return new ResponseDTO(userDTO, quizResponseDTO);
                } else {
                    //System.out.println("No example found for quiz ID: " + quiz.getId());
                    throw new RuntimeException("No example found for quiz ID: " + quiz.getId()); // Throwing an exception to be handled by Spring
                }
            } else {
                //System.out.println("No quiz found.");
                throw new RuntimeException("No quiz found"); // Throwing an exception to be handled by Spring
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while fetching the next quiz", e);
        }
    }
}