package com.sparta.schedulemanagement_spring.error;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
