package KBOT.kboTalk.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * <회원 가입>
     * 1. 아이디 중복 확인
     * 2. 닉네임 중복 확인
     * 3. 비밀번호 확인
     * 4. 회원 저장
     */
    public Member joinMember(Member member) {
        // 1. 아이디 중복 확인
        verifyExistsUserId(member.getUserId());

        // 2. 닉네임 중복 확인
        verifyExistsNickname(member.getNickname());

        // TODO : 비밀번호 확인 요청 먼저 구현한 후에 구현할 것
        // 3. 비밀번호 확인

        // 4. 회원 저장
        return memberRepository.save(member);
    }

    /**
     * <닉네임 중복 확인>
     * 1. 닉네임 중복 확인
     * 2. 사용하고자 하는 닉네임이 존재하면 true,를 아니면 false를 리턴
     */
    public boolean checkNickname(String nickname) {
        // 1. 닉네임 중복 확인
        Optional<Member> findNickname = memberRepository.findByNickname(nickname);

        // 2. 사용하고자 하는 닉네임이 존재하면 true,를 아니면 false를 리턴
        if (findNickname.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    // 유저 아이디 중복 확인 메서드
    // TODO : 유저 아이디 중복인 경우 예외 처리
    private void verifyExistsUserId(String userId) {
        Optional<Member> findMember = memberRepository.findByUserId(userId);

        if (findMember.isPresent()) {
            log.info("아이디 중복");
            throw new RuntimeException();
        }
    }

    // 닉네임 중복 확인 메서드
    // TODO : 닉네임 중복인 경우 예외 처리
    private void verifyExistsNickname(String nickname) {
        Optional<Member> findMember = memberRepository.findByNickname(nickname);

        if (findMember.isPresent()) {
            log.info("닉네임 중복");
            throw new RuntimeException();
        }
    }

}
