package org.hhplus.hhpluscommnunity.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SignupRequestDto {
        @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "username의 형식이 올바르지 않습니다.")
        private String username;
        @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "password의 형식이 올바르지 않습니다.")
        private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LoginRequestDto {
        private String username;
        private String password;
    }
}
