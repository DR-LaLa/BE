package com.medicine.medicine.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Setter
public class ExampleEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEntity quiz;

    private String answer;
    private String a;
    private String b;
    private String c;

}
