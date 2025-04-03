package KBOT.kboTalk.web.interceptor;

import KBOT.kboTalk.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

// 로그인 인증 체크 인터셉터
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("# LoginCheckInterceptor");

        // 로그인 인증이 필요 없거나 핸들러가 존재하지 않는 경우 로그인 인증 로직은 생략(error or static resource)
        if (handler instanceof ResourceHttpRequestHandler || handler == null) {
            log.info("handler : {}", handler.getClass());
            return true;
        }

        log.info("인증 체크 인터셉터 실행 {}", request);
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            // 로그인으로 redirect
            response.sendRedirect("/members/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
