package com.sparta.schedulemanagement_spring.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private Long password;
    private String title;
    private String contents;
    private String manager;
    private Date date;
}
