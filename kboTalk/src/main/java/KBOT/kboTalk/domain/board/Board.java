package KBOT.kboTalk.domain.board;

import KBOT.kboTalk.domain.BaseEntity;
import KBOT.kboTalk.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column
    private String title; // 제목

    @Column
    @Lob
    private String content; // 내용

    @Column
    @Builder.Default // Builder는 무조건 해당 타입의 초기값으로 초기화 되기 대문에 0으로 초기화하기 위해 해당 애너테이션을 붙임
    private Long viewCount = 0L; // 조회수

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
