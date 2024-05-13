package com.medicine.medicine.controller;

import com.medicine.medicine.dto.LoginRequestDTO;
import com.medicine.medicine.dto.LoginResponseDTO;
import com.medicine.medicine.dto.SignupRequestDTO;
import com.medicine.medicine.entity.UserEntity;
import com.medicine.medicine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDTO> login(@PathVariable LoginRequestDTO request){
        UserEntity loginUser = userService.login(request);
        LoginResponseDTO responseDTO = new LoginResponseDTO(loginUser.getNickname());

        return ResponseEntity.ok(responseDTO);
    }
}
