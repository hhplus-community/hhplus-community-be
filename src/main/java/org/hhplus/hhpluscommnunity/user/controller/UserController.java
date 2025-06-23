package org.hhplus.hhpluscommnunity.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody UserDto.SignupRequestDto request) {
        return ResponseEntity.ok().body(userService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody UserDto.LoginRequestDto request) {
        return ResponseEntity.ok().body(userService.login(request));
    }
}
