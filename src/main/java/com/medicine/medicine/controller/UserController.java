package com.medicine.medicine.controller;

import com.medicine.medicine.dto.SignupRequestDTO;
import com.medicine.medicine.entity.UserEntity;
import com.medicine.medicine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> addUser(@RequestBody SignupRequestDTO request){
        UserEntity signupUser = userService.signup(request);

//        return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(signupUser);
    }
}
