package com.sparta.schedulemanagement_spring.controller;

import com.sparta.schedulemanagement_spring.dto.LoginRequestDto;
import com.sparta.schedulemanagement_spring.dto.SignupRequestDto;
import com.sparta.schedulemanagement_spring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public String signUp(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return "회원가입 성공";
    }

}
