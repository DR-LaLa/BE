package com.medicine.medicine.service;

import com.medicine.medicine.entity.UserEntity;
import com.medicine.medicine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RankService {

    private final UserRepository userRepository;

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }
}
