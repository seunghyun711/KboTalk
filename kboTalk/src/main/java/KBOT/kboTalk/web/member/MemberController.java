package KBOT.kboTalk.web.member;

import KBOT.kboTalk.domain.member.Member;
import KBOT.kboTalk.domain.member.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    // 회원 가입 페이지 조회
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") LoginDto.JoinDto dto) {
        return "members/joinForm";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") LoginDto.JoinDto dto, BindingResult result) {
        if (result.hasErrors()) { // 오류가 발생한 경우
            log.info("join error : {}", result.getAllErrors());
            return "members/joinForm";
        }

        Member member = memberMapper.joinDtoToMember(dto);
        log.info("member.getUserId : {}", member.getUserId());
        log.info("member.getPassword : {}", member.getPassword());
        memberService.joinMember(member);


        return "redirect:/";
    }

}
