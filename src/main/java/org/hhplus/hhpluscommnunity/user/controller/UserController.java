package org.hhplus.hhpluscommnunity.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hhplus.hhpluscommnunity.jwt.JwtProvider;
import org.hhplus.hhpluscommnunity.user.dto.ResponseDto;
import org.hhplus.hhpluscommnunity.user.dto.UserDto;
import org.hhplus.hhpluscommnunity.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody UserDto.SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok().body(ResponseDto.builder().message("회원가입에 성공했습니다.").build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody UserDto.LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok()
                .header(JwtProvider.AUTHORIZATION_HEADER, token)
                .body(ResponseDto.builder().message("로그인에 성공했습니다.").build());
    }
}
