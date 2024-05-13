package com.medicine.medicine.service;

import com.medicine.medicine.dto.SignupRequestDTO;
import com.medicine.medicine.entity.UserEntity;
import com.medicine.medicine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity signup(SignupRequestDTO request){
        return userRepository.save(request.toEntity());
    }
}
