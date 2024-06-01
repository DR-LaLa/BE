package com.medicine.medicine.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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

    @PrePersist
    @PreUpdate
    public void calculateLevel(){
        int count = this.count;

        int newLevel = count/30;

        if (count % 30 == 0 && count != 0) {
            newLevel--;
        }

        this.level = newLevel;
    }
}
