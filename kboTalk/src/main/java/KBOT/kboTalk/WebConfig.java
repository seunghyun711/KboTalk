package KBOT.kboTalk;

import KBOT.kboTalk.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                // 인증 인터셉터 제외 경로
                .excludePathPatterns("/", "/members/join", "/members/login", "/members/logout",
                        "/api/members/**",
                        "/js/**",
                        "/css/**", "/*.ico", "/error", "/error/**",
                        "/notice"
                );


    }
}
