package com.sparta.schedulemanagement_spring.controller;

import com.sparta.schedulemanagement_spring.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement_spring.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement_spring.service.ScheduleService;
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
    public ScheduleResponseDto addSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.addSchedule(requestDto);
    }

    // 특정 일정 조회하기
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {

        return scheduleService.getSchedule(id);
    }

    // 일정 조회하기
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules() {

        return scheduleService.getAllSchedules();
    }

    // 일정 수정하기
    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto editSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.editSchedule(id,requestDto);
    }

    // 일정 삭제하기
    @DeleteMapping("/schedules/{id}")
    public ScheduleResponseDto deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.deleteSchedule(id,requestDto);
    }
}
