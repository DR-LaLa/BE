package com.medicine.medicine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ConfirmIdResponseDTO {

    @JsonProperty("isSuccess")
    private boolean success;

}
