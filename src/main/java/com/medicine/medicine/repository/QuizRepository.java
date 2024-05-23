package com.medicine.medicine.repository;

import com.medicine.medicine.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    @Query(value = "SELECT * FROM Quiz ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<QuizEntity> findRandomQuiz();

    @Query(value = "SELECT * FROM Quiz WHERE id NOT IN (:excludedIds) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<QuizEntity> findRandomQuizExcludingIds(@Param("excludedIds") List<Long> excludedIds);
}

