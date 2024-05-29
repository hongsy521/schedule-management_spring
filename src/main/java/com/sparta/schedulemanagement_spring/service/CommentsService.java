package com.sparta.schedulemanagement_spring.service;

import com.sparta.schedulemanagement_spring.dto.CommentsRequestDto;
import com.sparta.schedulemanagement_spring.dto.CommentsResponseDto;
import com.sparta.schedulemanagement_spring.entity.Comments;
import com.sparta.schedulemanagement_spring.entity.Schedule;
import com.sparta.schedulemanagement_spring.repository.CommentsRepository;
import com.sparta.schedulemanagement_spring.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final ScheduleRepository scheduleRepository;
    private final CommentsRepository commentsRepository;

    public CommentsResponseDto addComment(Long scheduleId, CommentsRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->
                new IllegalArgumentException("일정을 찾을 수 없습니다."));

        Comments comment = new Comments(requestDto,schedule);
        Comments saveComment = commentsRepository.save(comment);
        CommentsResponseDto responseDto = new CommentsResponseDto(saveComment);

        return responseDto;
    }

    /*public List<CommentsResponseDto> getAllComments(int scheduleId) {
    }

    public CommentsResponseDto updateComment(int scheduleId, int commentId, CommentsRequestDto requestDto) {
    }

    public CommentsResponseDto deleteComment(int scheduleId, int commentId) {
    }*/
}

