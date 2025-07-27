package HouseWith.hwf.domain.Member;

import HouseWith.hwf.DTO.MyPage.MyPageDTO;
import HouseWith.hwf.domain.JoinRequest.JoinRequest;
import HouseWith.hwf.domain.LivingPattern.LivingPattern;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(
            fetch = FetchType.LAZY ,
            cascade = CascadeType.ALL
    )
    private List<JoinRequest> joinRequests = new ArrayList<>();

    @OneToOne
    private LivingPattern livingPattern;

    private String username;
    private String password;


    private String name;
    private String introduction_comment;
    private String phone;
    private String email;

    private String nickname;
    private String sex;
    private String dormitoryName;

    public Member(
            Long memberID ,
            String name ,
            String introduction_comment ,
            String phone ,
            String email ,
            String nickname ,
            String sex ,
            String dormitoryName
    ) {
        this.id = memberID;
        this.name = name;
        this.introduction_comment = introduction_comment;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.dormitoryName = dormitoryName;
    }

    //추후에 OAuth 를 통해 받아올 데이터들 저장할 흐름
    //미사용
//    public Member(Long memberId ,  String name, String phone, String email) {
//        this.id = memberId;
//
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//    }

    //회원 정보 받아오기
    public Member(
            String username ,
            String password ,
            String email ,
            String nickname ,
            String sex ,
            String dormitoryName
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.dormitoryName = dormitoryName;
    }

    //생활 패턴들 받아오기
    public void poll_LP(LivingPattern livingPattern) {
        this.livingPattern = livingPattern;
    }

    public void change_info(MyPageDTO myPageDTO) {
        this.nickname = myPageDTO.getNickname();
        this.introduction_comment = myPageDTO.getIntroduction_comment();
        this.dormitoryName = myPageDTO.getDormitoryName();
        this.livingPattern = myPageDTO.getLivingPattern();
    }
}
