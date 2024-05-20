package com.sparta.schedulemanagement_spring.dto;

import com.sparta.schedulemanagement_spring.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private Long password;
    private String title;
    private String contents;
    private String manager;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public ScheduleResponseDto(Schedule schedule) {
        this.id=schedule.getId();
        this.password=schedule.getPassword();
        this.title=schedule.getTitle();
        this.contents=schedule.getContents();
        this.manager=schedule.getManager();
        this.createdDate=schedule.getCreatedAt();
        this.modifiedDate=schedule.getModifiedAt();
    }
}
