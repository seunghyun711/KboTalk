package KBOT.kboTalk.domain.exception;

// 존재하지 않은 게시글을 요청한 경우 발생하는 커스텀 예외
public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException(String message) {
        super(message);
    }
}
