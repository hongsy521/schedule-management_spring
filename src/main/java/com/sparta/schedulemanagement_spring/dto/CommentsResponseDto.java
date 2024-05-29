package com.sparta.schedulemanagement_spring.dto;

import com.sparta.schedulemanagement_spring.entity.Comments;
import lombok.Getter;

@Getter
public class CommentsResponseDto {
    private String comment;

    public CommentsResponseDto(Comments comment) {
        this.comment=comment.getComment();
    }
}
