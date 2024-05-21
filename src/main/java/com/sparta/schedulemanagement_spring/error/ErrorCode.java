package com.sparta.schedulemanagement_spring.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_PASSWORD(400,"잘못된 비밀번호입니다. 비밀번호를 확인해주세요."),
    INVALID_SCHEDULE(400,"이미 삭제된 일정입니다."),
    SCHEDULE_NOT_FOUND(404,"존재하지 않는 일정입니다."),
    INTERNAL_SERVER_ERROR(500,"서버 에러입니다.");

    private  final int httpStatus;
    private final  String errorMessage;
}
