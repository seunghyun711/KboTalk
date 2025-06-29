package KBOT.kboTalk.web.advice;

import KBOT.kboTalk.domain.exception.BoardNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalBoardExceptionAdvice { // 게시판 예외 처리 클래스

    // BoardNotFoundException 처리
    @ExceptionHandler(BoardNotFoundException.class)
    public String handleBoardNotFoundException(BoardNotFoundException e, Model model) {
        model.addAttribute("message", e.getMessage());

        return "error/404"; // 404 에러 페이지 리턴
    }
}
