package com.sparta.schedulemanagement_spring.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.Date;

@Getter
public class SignupRequestDto {
    @NotBlank
    @Min(4)
    @Max(10)
    @Pattern(regexp = "^[0-9a-z]")
    private String username;
    @NotBlank
    @Min(8)
    @Max(15)
    @Pattern(regexp = "^[0-9a-zA-Z]")
    private String password;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
    private Date registrationDate;
}
