package com.sparta.schedulemanagement_spring.controller;

import com.sparta.schedulemanagement_spring.dto.CommentsRequestDto;
import com.sparta.schedulemanagement_spring.dto.CommentsResponseDto;
import com.sparta.schedulemanagement_spring.security.UserDetailsImpl;
import com.sparta.schedulemanagement_spring.service.CommentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    // 댓글 등록
    @PostMapping("/schedules/{scheduleId}/comments")
    public CommentsResponseDto addComment(@PathVariable Long scheduleId, @RequestBody @Valid CommentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.addComment(scheduleId,requestDto,userDetails.getUser());
    }

   // 댓글 조회
    @GetMapping("/schedules/{scheduleId}/comments")
    public List<CommentsResponseDto> getAllComments(@PathVariable Long scheduleId) {
        return commentsService.getAllComments(scheduleId);
    }

    // 댓글 수정
    @PutMapping("/schedules/{scheduleId}/comments/{commentId}")
    public CommentsResponseDto updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody @Valid CommentsRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.updateComment(scheduleId,commentId,requestDto,userDetails.getUser());
    }

    // 댓글 삭제
    @DeleteMapping("/schedules/{scheduleId}/comments/{commentId}")
    public String deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.deleteComment(scheduleId,commentId,userDetails.getUser());
    }
}
