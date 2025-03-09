package KBOT.kboTalk.web.member;

import KBOT.kboTalk.domain.member.Member;
import KBOT.kboTalk.domain.member.MemberRepository;
import KBOT.kboTalk.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
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
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    // 회원 가입 페이지 조회
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "members/joinForm";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") LoginDto.JoinDto dto, BindingResult result) {
        if (result.hasErrors()) { // 오류가 발생한 경우
            return "members/joinForm";
        }

        Member member = memberMapper.joinDtoToMember(dto);
        memberService.joinMember(member);

        return "redirect:/";
    }

}
