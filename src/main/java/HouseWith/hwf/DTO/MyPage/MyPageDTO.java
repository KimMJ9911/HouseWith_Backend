package HouseWith.hwf.DTO.MyPage;

import HouseWith.hwf.domain.LivingPattern.LivingPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPageDTO {
    private String name;
    private String introduction_comment;
    private String phone;
    private String email;

    private String nickname;
    private String sex;
    private String dormitoryName;

    private LivingPattern livingPattern;

    public MyPageDTO(MemberDTO memberDTO) {
        this.name = memberDTO.getName();
        this.introduction_comment = memberDTO.getIntroduction_comment();
        this.phone = memberDTO.getPhone();
        this.email = memberDTO.getEmail();
        this.nickname = memberDTO.getNickname();
        this.sex = memberDTO.getSex();
        this.dormitoryName = memberDTO.getDormitoryName();
        this.livingPattern = memberDTO.getLivingPattern();
    }
}
