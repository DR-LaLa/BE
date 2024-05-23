package com.medicine.medicine.controller;
import com.medicine.medicine.entity.ExampleEntity;
import com.medicine.medicine.entity.QuizEntity;
import com.medicine.medicine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/main/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/{loginid}")
    public Response getNextQuiz(@PathVariable String loginid) {
        try {
            System.out.println("Received request for user with loginid: " + loginid);
            UserEntity user = quizService.getUserByLoginid(loginid);
            if (user == null) {
                System.out.println("User not found with loginid: " + loginid);
                throw new RuntimeException("User not found"); // Throwing an exception to be handled by Spring
            }
            Integer count = user.getCount();
            System.out.println("User count for loginid " + loginid + ": " + count);

            List<Long> seenQuizIds = quizService.getSeenQuizIds(user);
            if (seenQuizIds == null) {
                seenQuizIds = new ArrayList<>();
            }
            System.out.println("Seen Quiz IDs: " + seenQuizIds);

            QuizEntity quiz = quizService.getRandomQuizExcludingSeen(user);
            if (quiz != null) {
                System.out.println("Found quiz: " + quiz.getProblem());
                ExampleEntity example = quizService.getExample(quiz.getId());
                if (example != null) {
                    System.out.println("Found example for quiz ID " + quiz.getId() + ": " + example.getAnswer());

                    // Mark the quiz as seen for the user
                    quizService.markQuizAsSeen(user, quiz);

                    return new Response(new User(count), new QuizResponse(quiz.getId(), quiz.getCategory(), quiz.getProblem(), quiz.getExplanation(), example));
                } else {
                    System.out.println("No example found for quiz ID: " + quiz.getId());
                    throw new RuntimeException("No example found for quiz ID: " + quiz.getId()); // Throwing an exception to be handled by Spring
                }
            } else {
                System.out.println("No quiz found.");
                throw new RuntimeException("No quiz found"); // Throwing an exception to be handled by Spring
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while fetching the next quiz", e);
        }
    }
}

class Response {
    private User user;
    private QuizResponse quiz;

    public Response(User user, QuizResponse quiz) {
        this.user = user;
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuizResponse getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizResponse quiz) {
        this.quiz = quiz;
    }
}

class User {
    private Integer count;

    public User(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

class QuizResponse {
    private Long id;
    private String category;
    private String problem;
    private String explanation;
    private Example example;

    public QuizResponse(Long id, String category, String problem, String explanation, ExampleEntity exampleEntity) {
        this.id = id;
        this.category = category;
        this.problem = problem;
        this.explanation = explanation;
        this.example = new Example(exampleEntity.getAnswer(), exampleEntity.getA(), exampleEntity.getB(), exampleEntity.getC());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
}

class Example {
    private String answer;
    private String example_a;
    private String example_b;
    private String example_c;

    public Example(String answer, String example_a, String example_b, String example_c) {
        this.answer = answer;
        this.example_a = example_a;
        this.example_b = example_b;
        this.example_c = example_c;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExample_a() {
        return example_a;
    }

    public void setExample_a(String example_a) {
        this.example_a = example_a;
    }

    public String getExample_b() {
        return example_b;
    }

    public void setExample_b(String example_b) {
        this.example_b = example_b;
    }

    public String getExample_c() {
        return example_c;
    }

    public void setExample_c(String example_c) {
        this.example_c = example_c;
    }
}
