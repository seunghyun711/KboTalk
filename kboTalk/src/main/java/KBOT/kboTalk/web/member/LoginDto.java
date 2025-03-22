package KBOT.kboTalk.web.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "ID를 입력하세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;
}
