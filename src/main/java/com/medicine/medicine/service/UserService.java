package com.medicine.medicine.service;

import com.medicine.medicine.dto.ConfirmIdRequestDTO;
import com.medicine.medicine.dto.LoginRequestDTO;
import com.medicine.medicine.dto.SignupRequestDTO;
import com.medicine.medicine.entity.UserEntity;
import com.medicine.medicine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity signup(SignupRequestDTO request){
        return userRepository.save(request.toEntity());
    }

    public UserEntity login(LoginRequestDTO request){

        Optional<UserEntity> optionalUser = userRepository.findByLoginid(request.getLoginid());

        if(optionalUser.isEmpty()){
            return null;
        }

        UserEntity userEntity = optionalUser.get();

        if(!userEntity.getPassword().equals(request.getPassword())){
            return null;
        }

        return userEntity;
    }
}
