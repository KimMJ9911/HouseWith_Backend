package HouseWith.hwf.DTO.MyPage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPageMainDTO {
    //나중에 프로필 사진 추가
    private String nickname;
    private String introduction_comment;

    public MyPageMainDTO(String nickname, String introduction_comment) {
        this.nickname = nickname;
        this.introduction_comment = introduction_comment;
    }
}
