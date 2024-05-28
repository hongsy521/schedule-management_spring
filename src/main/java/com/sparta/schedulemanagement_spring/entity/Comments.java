package com.sparta.schedulemanagement_spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comments extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String comment;

    // 코멘트 하나당 사용자 한명 매치(일대일 단방향). Eager타입으로 즉시로딩 정보 바로 가져옴
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    // 일정 하나당 코멘트 여러개 매치 (다대일 양방향). 코멘트 조회할 때마다 즉시?
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
