package com.sparta.schedulemanagement_spring.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.Date;

@Getter
public class SignupRequestDto {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[0-9a-z]+$")
    private String username;
    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String password;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
}
