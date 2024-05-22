package com.medicine.medicine.repository;

import com.medicine.medicine.entity.ExampleEntity;
import com.medicine.medicine.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
    ExampleEntity findByQuiz(QuizEntity quiz);
}
