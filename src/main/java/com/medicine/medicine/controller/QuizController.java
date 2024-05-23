package com.medicine.medicine.controller;

import com.medicine.medicine.entity.ExampleEntity;
import com.medicine.medicine.entity.QuizEntity;
import com.medicine.medicine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//첫 번째 퀴즈 가져오기: /main/quiz
//다음 퀴즈 가져오기: /main/quiz?lastId=1


@RestController
@RequestMapping("/main/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public QuizResponse getNextQuiz(@RequestParam(required = false, defaultValue = "0") Long lastId) {
        QuizEntity quiz = quizService.getNextQuiz(lastId);
        if (quiz != null) {
            ExampleEntity example = quizService.getExample(quiz.getId());
            if (example != null) {
                return new QuizResponse(quiz.getId(), quiz.getCategory(), quiz.getProblem(), quiz.getExplanation(), example);
            }
        }
        return null;
    }
}

class QuizResponse {
    private Long id;
    private String category;
    private String problem;
    private String explanation;
    private ExampleEntity example;

    public QuizResponse(Long id, String category, String problem, String explanation, ExampleEntity example) {
        this.id = id;
        this.category = category;
        this.problem = problem;
        this.explanation = explanation;
        this.example = example;
    }
}

