package org.hhplus.hhpluscommnunity.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hhplus.hhpluscommnunity.jwt.JwtProvider;
import org.hhplus.hhpluscommnunity.user.dto.UserDto;
import org.hhplus.hhpluscommnunity.user.entity.User;
import org.hhplus.hhpluscommnunity.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signup(UserDto.SignupRequest request) {
        checkDuplicateUsername(request);

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public String login(UserDto.LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        }

        return jwtProvider.createToken(user.getUsername());
    }

    private void checkDuplicateUsername(UserDto.SignupRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new IllegalArgumentException("중복된 username 입니다.");
        }
    }
}
