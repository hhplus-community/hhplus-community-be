package org.hhplus.hhpluscommnunity.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;


public class UserDto {

    @Getter
    public static class SignupRequest {
        @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "username의 형식이 올바르지 않습니다.")
        private String username;
        @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "password의 형식이 올바르지 않습니다.")
        private String password;
    }

    @Getter
    public static class LoginRequest {
        private String username;
        private String password;
    }
}
