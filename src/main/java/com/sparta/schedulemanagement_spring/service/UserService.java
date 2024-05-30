package com.sparta.schedulemanagement_spring.service;

import com.sparta.schedulemanagement_spring.dto.LoginRequestDto;
import com.sparta.schedulemanagement_spring.dto.SignupRequestDto;
import com.sparta.schedulemanagement_spring.entity.User;
import com.sparta.schedulemanagement_spring.entity.UserRoleEnum;
import com.sparta.schedulemanagement_spring.jwt.JwtUtil;
import com.sparta.schedulemanagement_spring.repository.UserRepository;
import com.sparta.schedulemanagement_spring.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String nickname = requestDto.getNickname();

        // 중복된 username일 경우
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("동일한 이름의 사용자가 이미 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username,password,nickname,role);
        userRepository.save(user);

    }

    public String login(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        Optional<User> checkUser = userRepository.findByUsernameAndPassword(username,password);
        if(!checkUser.isPresent()){
            throw new IllegalArgumentException("사용자이름 또는 비밀번호가 잘못되었습니다.");
        }
        String tokenValue = jwtUtil.createToken(username,checkUser.get().getRole());
        return tokenValue;

    }
}
