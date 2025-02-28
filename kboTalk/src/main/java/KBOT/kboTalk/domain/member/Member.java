package KBOT.kboTalk.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}
