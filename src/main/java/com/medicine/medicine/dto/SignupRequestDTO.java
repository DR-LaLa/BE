package com.medicine.medicine.dto;

import com.medicine.medicine.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignupRequestDTO {

    private String loginid;

    private String password;

    private String nickname;

    private Integer count;

    private Integer level;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .loginid(loginid)
                .password(password)
                .nickname(nickname)
                .count(count)
                .level(level)
                .build();
    }
}
