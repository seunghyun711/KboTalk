package KBOT.kboTalk;

import KBOT.kboTalk.domain.member.Member;
import KBOT.kboTalk.domain.member.MemberRepository;
import KBOT.kboTalk.domain.member.SessionMember;
import KBOT.kboTalk.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String mainPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)SessionMember sessionMember, Model model) {

        // 로그인 상태가 아닌 경우 메인 페이지로 바로 이동
        if (sessionMember == null) {
            return "main";
        }

        // 로그인한 사용자의 정보를 넘김
        model.addAttribute("member", sessionMember);
        return "main";
    }
}
