package KBOT.kboTalk.domain.comment;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column
    private String content; // 댓글 내용
}
