package com.sparta.schedulemanagement_spring.service;

import com.sparta.schedulemanagement_spring.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement_spring.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement_spring.entity.Schedule;
import com.sparta.schedulemanagement_spring.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto addSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAllByOrderByModifiedAtDesc()
                .stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto getSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto editSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
        Schedule schedule = findScheduleById(scheduleId);
        if(schedule.getPassword().equals(requestDto.getPassword())){
            schedule.update(requestDto);
        }else {
            throw new IllegalArgumentException("wrong password");
        }
        return new ScheduleResponseDto(schedule);
    }


    public ScheduleResponseDto deleteSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
        Schedule schedule = findScheduleById(scheduleId);
        if(schedule.getPassword().equals(requestDto.getPassword())){
            scheduleRepository.delete(schedule);
        }else {
            throw new IllegalArgumentException("wrong password");
        }
        return new ScheduleResponseDto(schedule);
    }
    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("일정이 존재하지 않습니다."));
    }
}
