package KBOT.kboTalk.domain.board;

import KBOT.kboTalk.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column
    private String title; // 제목

    @Column
    private String content; // 내용

    @Column
    private Long viewCount; // 조회수

    @Column
    private Long recommend; // 추천수

    @Column
    private String boardType; // 커뮤니티 타입

    @Column
    private String imageUrl; // 이미지 url

    // Board - Member 다대일 관계로 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // Board - BoardCategory 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private BoardCategory category;
}
