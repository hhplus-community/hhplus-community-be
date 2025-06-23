package org.hhplus.hhpluscommnunity.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hhplus.hhpluscommnunity.user.dto.ResponseDto;
import org.hhplus.hhpluscommnunity.user.dto.UserDto;
import org.hhplus.hhpluscommnunity.user.entity.User;
import org.hhplus.hhpluscommnunity.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto signup(UserDto.SignupRequestDto request) {
        checkDuplicateUsername(request);

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
        return ResponseDto.builder().message("회원가입에 성공했습니다.").build();
    }

    @Transactional
    public ResponseDto login(UserDto.LoginRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        }

        return ResponseDto.builder().message("로그인에 성공했습니다.").build();
    }

    private void checkDuplicateUsername(UserDto.SignupRequestDto request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new IllegalArgumentException("중복된 username 입니다.");
        }
    }
}
