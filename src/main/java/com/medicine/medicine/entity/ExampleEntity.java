package com.medicine.medicine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "example")
@Getter
@NoArgsConstructor
public class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEntity quiz;

    private String answer;
    private String a;
    private String b;
    private String c;
}
