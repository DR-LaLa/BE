package com.medicine.medicine.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginid;

    private String password;

    private String nickname;

    private Integer count;

    private Integer level;

    @Builder
    public UserEntity(String loginid, String password, String nickname, Integer count, Integer level){
        this.loginid = loginid;
        this.password = password;
        this.nickname = nickname;
        this.count = count;
        this.level = level;
    }
}
