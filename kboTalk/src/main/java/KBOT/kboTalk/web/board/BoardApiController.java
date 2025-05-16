package KBOT.kboTalk.web.board;

import KBOT.kboTalk.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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

    // 게시글 이미지 삭제 요청
    @PostMapping("/deleteImage")
    public ResponseEntity<String> deleteImage(@RequestBody Map<String, String> request) {
        String imageUrl = request.get("imageUrl");
        boardService.deleteImageByUrl(imageUrl);

        return ResponseEntity.ok("이미지 삭제 완료");
    }
}
