package com.sparta.schedulemanagement_spring.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {
    @NotBlank
    private Long password;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    @NotBlank
    private String manager;
    private Date date;
}
