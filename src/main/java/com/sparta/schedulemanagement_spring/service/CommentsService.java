package com.sparta.schedulemanagement_spring.service;

import com.sparta.schedulemanagement_spring.dto.CommentsRequestDto;
import com.sparta.schedulemanagement_spring.dto.CommentsResponseDto;
import com.sparta.schedulemanagement_spring.entity.Comments;
import com.sparta.schedulemanagement_spring.entity.Schedule;
import com.sparta.schedulemanagement_spring.entity.User;
import com.sparta.schedulemanagement_spring.repository.CommentsRepository;
import com.sparta.schedulemanagement_spring.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    /**
     * CommentsService에서 ScheduleRepository와 CommentsRepository를 직접 주입중
     * -> 직접 데이터베이스에 접근중
     */
    // private final ScheduleRepository scheduleRepository;
    private final CommentsRepository commentsRepository;
    private final ScheduleService scheduleService; // 추가: ScheduleService 주입

    public CommentsResponseDto addComment(Long scheduleId, CommentsRequestDto requestDto, User user) {
//        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->
//                new IllegalArgumentException("일정을 찾을 수 없습니다."));
        // scheduleRepository가 아닌 서비스로 이용!
        Schedule schedule = scheduleService.findScheduleById(scheduleId);


        Comments comment = new Comments(requestDto,schedule,user);
        Comments saveComment = commentsRepository.save(comment);
        CommentsResponseDto responseDto = new CommentsResponseDto(saveComment);

        return responseDto;
    }

    public List<CommentsResponseDto> getAllComments(Long scheduleId) {
//        Schedule schedule=scheduleRepository.findById(scheduleId).orElseThrow(()->
//                new IllegalArgumentException("일정을 찾을 수 없습니다."));
        Schedule schedule = scheduleService.findScheduleById(scheduleId);

        List<Comments> commentsList = commentsRepository.findAllByScheduleId(schedule.getId());
        List<CommentsResponseDto> responseDtoList = new ArrayList<>();
        for (Comments comments : commentsList) {
            responseDtoList.add(new CommentsResponseDto(comments));
        }
        return responseDtoList;
    }


    public CommentsResponseDto updateComment(Long scheduleId, Long commentId, CommentsRequestDto requestDto, User user) {
//        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->
//                new IllegalArgumentException("일정을 찾을 수 없습니다."));
        Schedule schedule = scheduleService.findScheduleById(scheduleId);

        // 일정이 존재할 경우에만
        // 선택한 댓글의 작성자와 현재 로그인한 사용자가 같을 때만
        Comments comments = commentsRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        if(comments.getUser().getId() != user.getId()){
            throw new IllegalArgumentException("댓글 작성자가 아니므로 수정할 수 없습니다.");
        }
        // 댓글 내용만 수정
        comments.setComment(requestDto.getComment());
        // DB에 수정된 댓글 저장
        Comments updateComments = commentsRepository.save(comments);

        return new CommentsResponseDto(updateComments);
    }
    public String deleteComment(Long scheduleId, Long commentId, User user) {
//        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->
//                new IllegalArgumentException("일정을 찾을 수 없습니다."));
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        // 일정이 존재할 경우에만
        // 선택한 댓글의 작성자와 현재 로그인한 사용자가 같을 때만
        Comments comments = commentsRepository.findById(commentId).orElseThrow(()->
                new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if(comments.getUser().getId() != user.getId()){
            throw new IllegalArgumentException("댓글 작성자가 아니므로 삭제할 수 없습니다.");
        }

        commentsRepository.delete(comments);


        return "댓글 삭제에 성공하였습니다.";
    }

}

