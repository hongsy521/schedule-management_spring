package com.sparta.schedulemanagement_spring.controller;

import com.sparta.schedulemanagement_spring.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement_spring.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement_spring.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 등록하기
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> addSchedule(@RequestBody @Valid ScheduleRequestDto requestDto) {

        return ResponseEntity.ok(scheduleService.addSchedule(requestDto));
    }

    // 특정 일정 조회하기
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long scheduleId) {

        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    // 일정 조회하기
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {

        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    // 일정 수정하기
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> editSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleRequestDto requestDto) {

        return ResponseEntity.ok(scheduleService.editSchedule(scheduleId,requestDto));
    }

    // 일정 삭제하기
    @ExceptionHandler(IllegalArgumentException.class)
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleRequestDto requestDto) {

        return ResponseEntity.ok(scheduleService.deleteSchedule(scheduleId,requestDto));
    }
}
