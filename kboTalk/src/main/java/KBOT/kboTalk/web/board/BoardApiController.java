package KBOT.kboTalk.web.board;

import KBOT.kboTalk.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor

public class BoardApiController {
    private final BoardService boardService;

    // 게시글 이미지 저장 요청
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile img) {
        return boardService.saveImg(img);
    }
}
