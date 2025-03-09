package KBOT.kboTalk.web.member;

import KBOT.kboTalk.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    // JoinForm -> Member
    public Member joinDtoToMember(LoginDto.JoinDto dto){
        if (dto == null) {
            return null;
        }else{
            Member member = new Member();
            member.setUserId(dto.getUserId());
            member.setNickname(dto.getNickname());
            member.setPassword(dto.getPassword());
            member.setMemberType(dto.getMemberType());
            member.setProfileImgUrl(dto.getProfileImage());

            return member;
        }
    }
}
