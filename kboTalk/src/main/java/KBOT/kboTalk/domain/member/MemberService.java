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
     * <로그인>
     * 1. 입력한 유저 아이디를 가진 유저 조회
     * 2. 해당 유저의 비밀번호와 입력받은 비밀번호 비교
     */
    public Member login(Member member) {

        // 1. 입력한 유저 아이디를 가진 유저 조회
        Member findMember = memberRepository.findByUserId(member.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없음."));

        // 입력받은 아이디가 존재하지 않는 아이디이면 null을 리턴
        if (findMember == null) {
            return null;
        }

        // 2. 해당 유저의 비밀번호와 입력받은 비밀번호 비교
        if (findMember.getPassword().equals(member.getPassword())) { // 비밀번호가 일치한 경우 해당 member 리턴
            return findMember;
        } else { // 비밀번호가 일치하지 않는 경우 null 리턴
            return null;
        }

    }

    /**
     * <유저 아이디 중복 확인>
     * 1. 유저 아이디 중복 확인
     * 2. 사용하고자 하는 닉네임이 존재하면 true를, 아니면 false를 리턴
     */
    public boolean checkUserId(String userId) {
        // 1. 유저 아이디 중복 확인
        Optional<Member> findUserId = memberRepository.findByUserId(userId);

        // 2. 사용하고자 하는 닉네임이 존재하면 true를, 아니면 false를 리턴
        if (findUserId.isPresent()) {
            return true;
        } else {
            return false;
        }
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
