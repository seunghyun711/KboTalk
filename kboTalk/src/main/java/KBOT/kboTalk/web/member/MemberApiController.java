package KBOT.kboTalk.web.member;

import KBOT.kboTalk.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    // 회원 아이디 중복 확인 TODO : 예외 처리 추가
    @GetMapping("/checkUserId")
    public ResponseEntity<HttpStatus> checkUserId(@RequestParam("userId") String userId) {
        boolean isExistUserId = memberService.checkUserId(userId);

        // 닉네임 존재 여부에 따라 다른 상태 리턴
        if (isExistUserId) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // 닉네임 중복 확인 TODO : 예외 처리 추가
    @GetMapping("/checkNickname")
    public ResponseEntity<HttpStatus> checkNickname(@RequestParam("nickname") String nickname) {
        boolean isExistNickname = memberService.checkNickname(nickname);

        // 닉네임 존재 여부에 따라 다른 상태를 리턴
        if (isExistNickname) { // 닉네임 중복인 경우
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else { // 닉네임이 중복되지 않은 경우
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
