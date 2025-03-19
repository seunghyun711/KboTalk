package KBOT.kboTalk.web.member;

import KBOT.kboTalk.domain.member.Member;
import KBOT.kboTalk.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    // 회원 가입 페이지 조회
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") LoginDto dto) {
        return "members/joinForm";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") LoginDto dto, BindingResult result) {

        // 비밀번호 검증
        if (!dto.isPasswordMatch()) {
            result.rejectValue("checkPassword", "error - JoinDto", "비밀번호가 일치하지 않습니다.");
        }

        if (result.hasErrors()) { // 오류가 발생한 경우
            log.info("join error : {}", result.getAllErrors());
            return "members/joinForm";
        }

        Member member = memberMapper.loginDtoToMember(dto);
        memberService.joinMember(member);

        return "redirect:/";
    }

}
