package org.hhplus.hhpluscommnunity.config;

import lombok.RequiredArgsConstructor;
import org.hhplus.hhpluscommnunity.jwt.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")        // 검사할 URL
                .excludePathPatterns("/api/auth/**");  // 로그인/회원가입 등 제외
    }
}
