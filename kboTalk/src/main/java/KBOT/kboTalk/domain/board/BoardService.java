package KBOT.kboTalk.domain.board;

import KBOT.kboTalk.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * <게시글 작성>
     * TODO : 작성자 관련 정보도 추가할 것
     */
    public void createBoard(Board board) {
        boardRepository.save(board);
    }
}
