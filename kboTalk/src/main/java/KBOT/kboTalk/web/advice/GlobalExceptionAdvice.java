package KBOT.kboTalk.web.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // 전역 예외 처리
@Slf4j
public class GlobalExceptionAdvice {

    // 이미지 파일 크기 관련 예외 처리
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, Object>> handleMaxSizeException(MaxUploadSizeExceededException e){
        Map<String, Object> errorBody = new HashMap<>();
        log.error("파일 크기 초과 예외 발생", e);
        errorBody.put("status", HttpStatus.PAYLOAD_TOO_LARGE); // 413 에러
        errorBody.put("error", "Payload Too Large");
        errorBody.put("message", "파일의 크기가 너무 큽니다. (최대 5MB)");

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorBody);
    }
}
