package com.sparta.schedulemanagement_spring.dto;

import com.sparta.schedulemanagement_spring.entity.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {
    private Long id;
    private String comment;
    private Long userId;
    private Long scheduleId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentsResponseDto(Comments comment) {
        this.id = comment.getId();
        this.comment=comment.getComment();
        //this.userId = comment.getUser().getId();
        this.scheduleId = comment.getSchedule().getId();
        this.createdDate=comment.getCreatedAt();
        this.modifiedDate=comment.getModifiedAt();
    }
}
