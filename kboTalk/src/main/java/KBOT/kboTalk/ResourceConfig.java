package KBOT.kboTalk;

import KBOT.kboTalk.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 정적 리소스 설정
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 브라우저에서 /upload/**로 정적 리소스를 요청하면 실제 파일 경로에 접근하여 해당 리소스를 가져옴
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/C:/kbt/uploads/");
    }
}
