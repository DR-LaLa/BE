package com.medicine.medicine.dto;
public class UserDTO {
    private Integer count;

    public UserDTO() {}

    public UserDTO(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}