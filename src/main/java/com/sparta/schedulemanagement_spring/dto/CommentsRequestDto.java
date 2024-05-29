package com.sparta.schedulemanagement_spring.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CommentsRequestDto {
    @NotBlank
    private String comment;
}
