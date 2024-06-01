package com.medicine.medicine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seenquiz")
@Setter
@Getter
public class SeenQuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loginid", referencedColumnName = "loginid")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEntity quiz;

    public SeenQuizEntity() {}

    public SeenQuizEntity(UserEntity user, QuizEntity quiz) {
        this.user = user;
        this.quiz = quiz;
    }
}

