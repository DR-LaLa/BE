package com.medicine.medicine.repository;

import com.medicine.medicine.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    Optional<QuizEntity> findFirstByIdGreaterThanOrderByIdAsc(Long lastId);
}
