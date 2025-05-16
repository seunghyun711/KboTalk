package KBOT.kboTalk.web.board;

import KBOT.kboTalk.domain.board.Board;
import KBOT.kboTalk.domain.board.BoardCategory;
import KBOT.kboTalk.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {
    // BoardCreateDto -> Board
    public Board boardCreateDtoToBoard(BoardCreateDto dto, BoardCategory category, Member member) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(category)
                .member(member)
                .build();
    }

}
