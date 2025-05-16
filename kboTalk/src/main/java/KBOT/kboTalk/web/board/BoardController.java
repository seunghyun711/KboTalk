package KBOT.kboTalk.web.board;

import KBOT.kboTalk.domain.board.Board;
import KBOT.kboTalk.domain.board.BoardCategory;
import KBOT.kboTalk.domain.board.BoardCategoryRepository;
import KBOT.kboTalk.domain.board.BoardService;
import KBOT.kboTalk.domain.member.Member;
import KBOT.kboTalk.domain.member.MemberRepository;
import KBOT.kboTalk.domain.member.SessionMember;
import KBOT.kboTalk.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final BoardCategoryRepository boardCategoryRepository;
    private final MemberRepository memberRepository;

    // 게시글 작성 페이지 조회
    @GetMapping("/new")
    public String boardForm(@ModelAttribute("board") BoardCreateDto dto, BindingResult bindingResult) {
        return "board/createForm";
    }

    // 게시글 작성
    @PostMapping("/new")
    public String createBoard(@Validated @ModelAttribute BoardCreateDto dto, BindingResult bindingResult,
                              @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) SessionMember loginMember) {

        if (bindingResult.hasErrors()) {
            log.info("create board error : {}", bindingResult.getAllErrors());
            return "board/createForm";
        }

        if (loginMember == null) {
            return "redirect:/members/loginForm"; // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
        }

        Member member = memberRepository.findById(loginMember.getMemberId()).orElseThrow();

        BoardCategory category = boardCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        Board board = boardMapper.boardCreateDtoToBoard(dto, category, member);

        boardService.createBoard(board);

        return "redirect:/";
    }

}
