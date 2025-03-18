package KBOT.kboTalk.web.member;

import KBOT.kboTalk.domain.member.MemberStatus;
import KBOT.kboTalk.domain.member.MemberType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LoginDto { // 로그인 관련 dto

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class JoinDto { // 회원가입 dto
        @NotBlank
        private String userId; // 회원 아이디

        @NotBlank
        private String password; // 비밀 번호

        @NotBlank
        private String checkPassword; // 비밀번호 검증을 위한 필드

        @NotBlank
        private String nickname; // 닉네임

        private String profileImage; // 프로필 이미지
    }


}
