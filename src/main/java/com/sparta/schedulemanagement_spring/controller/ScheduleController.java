package com.sparta.schedulemanagement_spring.controller;

import com.sparta.schedulemanagement_spring.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement_spring.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement_spring.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    // 특정 일정 조회하기
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleList.get(id);
        Long scheduleId=schedule.getId();
        String title=schedule.getTitle();
        String contents=schedule.getContents();
        String manager=schedule.getManager();
        Date date=schedule.getDate();
        // password는 보이지 않게 함
        ScheduleResponseDto responseDto = new ScheduleResponseDto(scheduleId,title,contents,manager,date);
        return responseDto;
    }

    // 일정 조회하기
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules() {
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new)
                 .toList();

        return responseList;
    }

    // 일정 수정하기
    @PutMapping("/schedules/{id}")
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
