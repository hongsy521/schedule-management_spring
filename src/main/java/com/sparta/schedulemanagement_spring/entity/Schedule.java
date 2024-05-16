package com.sparta.schedulemanagement_spring.entity;

import com.sparta.schedulemanagement_spring.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private Long password;
    private String title;
    private String contents;
    private String manager;
    private Date date;

    public Schedule(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager= requestDto.getManager();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.date = requestDto.getDate();

    }
}
