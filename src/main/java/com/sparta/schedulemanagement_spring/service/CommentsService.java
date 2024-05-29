package com.sparta.schedulemanagement_spring.service;

import com.sparta.schedulemanagement_spring.dto.CommentsRequestDto;
import com.sparta.schedulemanagement_spring.dto.CommentsResponseDto;
import com.sparta.schedulemanagement_spring.entity.Comments;
import com.sparta.schedulemanagement_spring.entity.Schedule;
import com.sparta.schedulemanagement_spring.repository.CommentsRepository;
import com.sparta.schedulemanagement_spring.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<CommentsResponseDto> getAllComments(Long scheduleId) {
        Schedule schedule=scheduleRepository.findById(scheduleId).orElseThrow(()->
                new IllegalArgumentException("일정을 찾을 수 없습니다."));

        List<Comments> commentsList = commentsRepository.findAllByScheduleId(schedule.getId());
        List<CommentsResponseDto> responseDtoList = new ArrayList<>();
        for (Comments comments : commentsList) {
            responseDtoList.add(new CommentsResponseDto(comments));
        }
        return responseDtoList;
    }


    public CommentsResponseDto updateComment(Long scheduleId, Long commentId, CommentsRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->
                new IllegalArgumentException("일정을 찾을 수 없습니다."));
        // 일정이 존재할 경우에만
            Comments comments = commentsRepository.findById(commentId).orElseThrow(() ->
                    new IllegalArgumentException("댓글을 찾을 수 없습니다."));
            // 댓글 내용만 수정
            comments.setComment(requestDto.getComment());
            // DB에 수정된 댓글 저장
            Comments updateComments = commentsRepository.save(comments);

        return new CommentsResponseDto(updateComments);
    }
    public String deleteComment(Long scheduleId, Long commentId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->
                new IllegalArgumentException("일정을 찾을 수 없습니다."));
        // 일정이 존재할 경우에만
            Comments comments = commentsRepository.findById(commentId).orElseThrow(()->
                    new IllegalArgumentException("댓글을 찾을 수 없습니다."));

            try {
                commentsRepository.delete(comments);
            }catch (EmptyResultDataAccessException e){
                e.getMessage();
            }

        return "일정 삭제에 성공하였습니다.";
    }


}

