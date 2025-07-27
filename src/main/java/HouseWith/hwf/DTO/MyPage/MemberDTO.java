package HouseWith.hwf.DTO.MyPage;

import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import HouseWith.hwf.domain.LivingPattern.LivingPattern;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MemberDTO {
    //나중에 프로필 사진 추가
    private Long Id;
    private String name;
    private String introduction_comment;
    private String phone;
    private String email;

    private String nickname;
    private String sex;
    private String dormitoryName;

    private LivingPattern livingPattern;

    @QueryProjection
    public MemberDTO(
            Long memberId ,
            String name,
            String introduction_comment ,
            String phone ,
            String email ,
            String nickname ,
            String sex ,
            String dormitoryName ,
            LivingPattern livingPattern) {
        this.Id = memberId;
        this.name = name;
        this.introduction_comment = introduction_comment;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.dormitoryName = dormitoryName;
        this.livingPattern = livingPattern;
    }
}
