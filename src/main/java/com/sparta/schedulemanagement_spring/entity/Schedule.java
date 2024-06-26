package com.sparta.schedulemanagement_spring.entity;

import com.sparta.schedulemanagement_spring.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="schedule")
@NoArgsConstructor
public class Schedule extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="password" , nullable = false)
    private String password;
    @Column(name="title" , nullable = false , length = 500)
    private String title;
    @Column(name="contents" , nullable = false , length = 500)
    private String contents;
    @Column(name="manager" , nullable = false)
    private String manager;

    // 일대다 양방향
    @OneToMany(mappedBy = "schedule")
    private List<Comments> commentsList= new ArrayList<>();

    public Schedule(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager= requestDto.getManager();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();

    }
}