package KBOT.kboTalk.web.member;

import KBOT.kboTalk.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    // JoinDto -> Member
    public Member joinDtoToMember(JoinDto dto){
        if (dto == null) {
            return null;
        }else{
            Member member = new Member();
            member.setUserId(dto.getUserId());
            member.setNickname(dto.getNickname());
            member.setPassword(dto.getPassword());
            member.setProfileImgUrl(dto.getProfileImage());

            return member;
        }
    }

    // LoginDto -> Member
    public Member loginDtoToMember(LoginDto dto) {
        if (dto == null) {
            return null;
        } else {
            Member member = new Member();
            member.setUserId(dto.getUserId());
            member.setPassword(dto.getPassword());

            return member;
        }
    }
}
