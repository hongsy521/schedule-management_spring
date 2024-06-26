package com.sparta.schedulemanagement_spring.entity;

import com.sparta.schedulemanagement_spring.dto.CommentsRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
@NoArgsConstructor
public class Comments extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = false)
    private String comment;

    // 사용자 한명은 여러개의 댓글 작성 가능 (다대일 단방향). Eager타입으로 즉시로딩 정보 바로 가져옴
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 일정 하나당 코멘트 여러개 매치 (다대일 양방향). 코멘트 조회할 때마다 즉시?
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comments(CommentsRequestDto requestDto, Schedule schedule,User user) {
        this.comment = requestDto.getComment();
        this.schedule = schedule;
        this.user = user;
    }
}
