package com.sparta.schedulemanagement_spring.dto;

import com.sparta.schedulemanagement_spring.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private Date date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id=schedule.getId();
        this.title=schedule.getTitle();
        this.contents=schedule.getContents();
        this.date=schedule.getDate();
    }
}
