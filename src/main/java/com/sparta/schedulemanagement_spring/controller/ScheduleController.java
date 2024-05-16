package com.sparta.schedulemanagement_spring.controller;

import com.sparta.schedulemanagement_spring.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement_spring.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement_spring.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    // 일정 등록하기
    @PostMapping("/schedules")
    public ScheduleResponseDto addSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        // 최대 Id 찾고 id 부여
        Long maxId=scheduleList.size()>0? Collections.max(scheduleList.keySet()) +1 : 1;
        schedule.setId(maxId);

        // DB 저장
        scheduleList.put(schedule.getId(), schedule);

        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

    // 일정 조회하기
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules() {
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return responseList;
    }

    // 일정 수정하기
    @PutMapping("/schedules/{scheduleId}")
    public Long editSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        if(scheduleList.containsKey(id)){
            Schedule schedule = scheduleList.get(id);
            schedule.update(requestDto);
            return schedule.getId();
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }
}
