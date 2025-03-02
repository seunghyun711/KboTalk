package KBOT.kboTalk.domain.notice;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NOTICE_ID")
    private Long id;

    @Column
    private String title; // 제목

    @Column
    private String content; // 내용
}
