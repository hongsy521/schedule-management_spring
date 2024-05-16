package com.sparta.schedulemanagement_spring.dto;

import com.sparta.schedulemanagement_spring.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private Long password;
    private String title;
    private String contents;
    private String manager;
    private Date date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id=schedule.getId();
        this.password=schedule.getPassword();
        this.title=schedule.getTitle();
        this.contents=schedule.getContents();
        this.manager=schedule.getManager();
        this.date=schedule.getDate();
    }


    public ScheduleResponseDto(Long scheduleId, String title, String contents, String manager, Date date) {
        this.id=scheduleId;
        this.title=title;
        this.contents=contents;
        this.manager=manager;
        this.date=date;
    }
}
