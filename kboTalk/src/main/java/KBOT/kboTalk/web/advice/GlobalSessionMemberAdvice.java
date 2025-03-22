package KBOT.kboTalk.web.advice;

import KBOT.kboTalk.domain.member.SessionMember;
import KBOT.kboTalk.web.SessionConst;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

// 모든 뷰에서 로그인 여부에 따라 sessionMember를 사용할 수 있도록 하는 클래스
@ControllerAdvice
public class GlobalSessionMemberAdvice {
    @ModelAttribute("sessionMember")
    public SessionMember sessionMember(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) SessionMember sessionMember) {
        return sessionMember;
    }
}
