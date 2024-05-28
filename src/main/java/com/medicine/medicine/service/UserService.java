package com.medicine.medicine.service;

import com.medicine.medicine.dto.ConfirmIdRequestDTO;
import com.medicine.medicine.dto.LoginRequestDTO;
import com.medicine.medicine.dto.SignupRequestDTO;
import com.medicine.medicine.dto.UpdateCountRequestDTO;
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

        UserEntity userEntity = userRepository.findByLoginid(request.getLoginid())
                .orElseThrow(() -> new RuntimeException("User not found with login id: " + request.getLoginid()));

        if(!userEntity.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid password for login id: " + request.getLoginid());
        }

        return userEntity;
    }

    public boolean confirmId(ConfirmIdRequestDTO request){

        Optional<UserEntity> optionalUser = userRepository.findByLoginid(request.getLoginid());

        return optionalUser.isEmpty();
    }

    public Integer quizCount(String loginid){

        UserEntity userEntity = userRepository.findByLoginid(loginid)
                .orElseThrow(() -> new RuntimeException("User not found with login id: " + loginid));

        return userEntity.getCount();
    }

    public UserEntity updateCount(String loginid, UpdateCountRequestDTO request){

        UserEntity userEntity = userRepository.findByLoginid(loginid)
                .orElseThrow(() -> new RuntimeException("User not found with loginid: " + loginid));

        userEntity.setCount(request.getCount());

        return userRepository.save(userEntity);

/*
            Optional<UserEntity> optionalUser = userRepository.findByLoginid(loginid);if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userEntity.setCount(count);

            int newLevel;

            if(count%30==0){
                newLevel = (count / 30) - 1;
            }
            else{
                newLevel = count / 30;
            }
            userEntity.setLevel(newLevel);

            return userRepository.save(userEntity);
        }
        else{
            throw new RuntimeException("User not found with loginid: " + loginid);
        }*/
    }
}
