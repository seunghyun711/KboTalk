package KBOT.kboTalk.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 세션에 들어갈 Member 객체의 정보들 중 필요한 정보만 담은 dto 클래스
@AllArgsConstructor
@Getter
public class SessionMember {
    private Long memberId;
    private String userId;
    private String nickname;
    private MemberType memberType;

    public SessionMember(Member member) {
        this.memberId = member.getId();
        this.userId = member.getUserId();
        this.nickname = member.getNickname();
        this.memberType = member.getMemberType();
    }
}
