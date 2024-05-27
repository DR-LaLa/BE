package com.medicine.medicine.dto;

public class ResponseDTO {
    private UserDTO user;
    private QuizResponseDTO quiz;

    public ResponseDTO() {}

    public ResponseDTO(UserDTO user, QuizResponseDTO quiz) {
        this.user = user;
        this.quiz = quiz;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public QuizResponseDTO getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizResponseDTO quiz) {
        this.quiz = quiz;
    }
}
