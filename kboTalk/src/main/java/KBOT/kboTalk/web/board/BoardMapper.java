package KBOT.kboTalk.web.board;

import KBOT.kboTalk.domain.board.Board;
import KBOT.kboTalk.domain.board.BoardCategory;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {
    // BoardCreateDto -> Board
    public Board boardCreateDtoToBoard(BoardCreateDto dto, BoardCategory category) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(category)
                .build();
    }

}
