package com.medicine.medicine.dto;

import com.medicine.medicine.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RankResponseDTO {

    private Long id;

    private String loginid;

    private String nickname;

    private Integer count;

    private Integer level;


    public RankResponseDTO(UserEntity userEntity){
        this.id = userEntity.getId();
        this.loginid = userEntity.getLoginid();
        this.nickname = userEntity.getNickname();
        this.count = userEntity.getCount();
        this.level = userEntity.getLevel();
    }
}
