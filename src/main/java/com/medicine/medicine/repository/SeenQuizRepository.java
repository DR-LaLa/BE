package com.medicine.medicine.repository;

import com.medicine.medicine.entity.SeenQuizEntity;
import com.medicine.medicine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface SeenQuizRepository extends JpaRepository<SeenQuizEntity, Long> {
    List<SeenQuizEntity> findByUser(UserEntity user);

    @Query(value = "SELECT quiz_id FROM seenquiz WHERE loginid = :loginid", nativeQuery = true)
    List<Long> findQuizIdsByLoginId(String loginid);
}
