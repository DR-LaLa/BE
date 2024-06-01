package com.medicine.medicine.controller;

import com.medicine.medicine.dto.RankResponseDTO;
import com.medicine.medicine.service.RankService;
import com.medicine.medicine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class RankController{

    private final RankService rankService;

    @GetMapping("/main/rank")
    public ResponseEntity<List<RankResponseDTO>> findAllUsers(){
        List<RankResponseDTO> users = rankService.findAll()
                .stream()
                .map(RankResponseDTO::new)
                .sorted((dto1, dto2) -> dto2.getCount().compareTo(dto1.getCount()))
                .toList();

        return ResponseEntity.ok()
                .body(users);
    }
}
