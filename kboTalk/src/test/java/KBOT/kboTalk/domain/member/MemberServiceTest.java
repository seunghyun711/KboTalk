package KBOT.kboTalk.domain.member;

import KBOT.kboTalk.web.member.LoginDto;
import KBOT.kboTalk.web.member.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberMapper mapper;

    @Autowired
    MemberServiceTest(MemberRepository memberRepository, MemberService memberService, MemberMapper mapper) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void save() {
        // given
        LoginDto dto = new LoginDto();
        dto.setUserId("test123");
        dto.setNickname("test");
        dto.setPassword("1234");
//        dto.setTeam("SSG Landers");
        dto.setCheckPassword("1234");
//        dto.setMemberType(MemberType.USER);
        dto.setProfileImage("ssg.img");

        Member member = mapper.loginDtoToMember(dto);

        // when
        Member newMember = memberService.joinMember(member);

        // then
        assertThat(newMember).isNotNull();
        assertThat(newMember.getUserId()).isEqualTo("test123");
        assertThat(newMember.getNickname()).isEqualTo("test");
    }

    @Test
    @DisplayName("닉네임 중복 검증 테스트")
    public void checkNickname() {
        // given
        String nickname = "hongdangmoo";

        // when
        memberService.checkNickname(nickname);

        // then
//        assertThat(nickname).isNotEqualTo("hongdnagmoo");
//        assertThat(nickname).isEqualTo("hongdnagmoo");
    }

    @Test
    @DisplayName("유저 아이디 중복 검증 테스트")
    public void checkUserId() {
        // given
        Member member = new Member();
        member.setUserId("hongdangmoo997");
        memberRepository.save(member);

        String userId = "hongdangmoo997";

        // when
        boolean isExistUserId = memberService.checkUserId(userId);

        // then
        System.out.println("isExistUserId = " + isExistUserId);
    }

    @Test
    @DisplayName("비밀번호 검증 테스트")
    public void checkPassword() {
        // given
        LoginDto dto = new LoginDto();
        dto.setPassword("1234");
        dto.setCheckPassword("1234");

        // when
        boolean passwordMatch = dto.isPasswordMatch();

        // then
        System.out.println("passwordMatch = " + passwordMatch);
    }
}