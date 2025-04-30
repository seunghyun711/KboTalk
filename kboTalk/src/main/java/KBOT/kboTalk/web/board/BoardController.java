package KBOT.kboTalk.web.board;

import KBOT.kboTalk.domain.board.Board;
import KBOT.kboTalk.domain.board.BoardCategory;
import KBOT.kboTalk.domain.board.BoardCategoryRepository;
import KBOT.kboTalk.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final BoardCategoryRepository boardCategoryRepository;

    // 게시글 작성 페이지 조회
    @GetMapping("/new")
    public String boardForm(@ModelAttribute("board") BoardCreateDto dto, BindingResult bindingResult) {
        return "board/createForm";
    }

    // 게시글 작성
    @PostMapping("/new")
    public String createBoard(@Validated @ModelAttribute BoardCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("create board error : {}", bindingResult.getAllErrors());
            return "board/createForm";
        }

        BoardCategory category = boardCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        Board board = boardMapper.boardCreateDtoToBoard(dto, category);

        boardService.createBoard(board);

        return "redirect:/";
    }

}
