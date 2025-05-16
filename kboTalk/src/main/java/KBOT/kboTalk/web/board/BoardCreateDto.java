package KBOT.kboTalk.web.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateDto {
    @NotBlank
    private String title; // 게시글 제목

    @NotBlank
    private String content; // 내용

    @NotNull
    private Long categoryId; // 카테고리
}
