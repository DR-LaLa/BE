package com.medicine.medicine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

@Entity
@Setter
public class Example {
    @Id
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private String answer;
    private String a;
    private String b;
    private String c;

}
