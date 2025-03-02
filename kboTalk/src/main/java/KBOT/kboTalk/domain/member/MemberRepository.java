package KBOT.kboTalk.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // userId를 통한 회원 조회
    Optional<Member> findByUserId(String userId);
}
