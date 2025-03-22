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
    public String mainPage() {
        return "main";
    }
}
