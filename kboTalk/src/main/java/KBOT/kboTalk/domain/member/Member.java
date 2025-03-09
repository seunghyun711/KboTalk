package KBOT.kboTalk.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id; // pk

    @Column
    private String userId; // 회원 id

    @Column
    private String password; // 비밀번호

    @Column
    private String nickname; // 닉네임

    @Column
    private String profileImgUrl; // 프로필 이미지 url

    @Column
    @Enumerated(EnumType.STRING)
    private MemberType memberType; // 회원 유형

    @Column
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.ACTIVE; // 회원 상태(기본 상태는 활동)

    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
