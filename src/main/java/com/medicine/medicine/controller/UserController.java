package com.medicine.medicine.controller;

import com.medicine.medicine.dto.*;
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
    public ResponseEntity<Void> addUser(@RequestBody SignupRequestDTO request){
        UserEntity signupUser = userService.signup(request);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        UserEntity loginUser = userService.login(request);

        if(loginUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        LoginResponseDTO response = new LoginResponseDTO(loginUser.getLoginid(), loginUser.getNickname());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup/confirmid")
    public ResponseEntity<ConfirmIdResponseDTO> confirmId(@RequestBody ConfirmIdRequestDTO request){
        boolean isSuccess = userService.confirmId(request);

        ConfirmIdResponseDTO response = new ConfirmIdResponseDTO(isSuccess);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/main/quizcount/{loginid}")
    public ResponseEntity<QuizCountResponseDTO> quizCount(@PathVariable String loginid){

        Integer count =  userService.quizCount(loginid);

        QuizCountResponseDTO response = new QuizCountResponseDTO(count);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/main/update/{loginid}")
    public ResponseEntity<Void> updateCount(@PathVariable String loginid, @RequestBody UpdateCountRequestDTO request){

        UserEntity updatedUser = userService.updateCount(loginid, request);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
