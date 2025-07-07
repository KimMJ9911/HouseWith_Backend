package HouseWith.hwf.domain.Member;

import HouseWith.hwf.domain.Article.Article;
import HouseWith.hwf.domain.JoinRequest.JoinRequest;
import HouseWith.hwf.domain.LivingPattern.LivingPattern;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

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
    private String phone;
    private String email;

    private String nickname;
    private String sex;
    private String dormitoryName;

    public Member(Long id , String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Member(
            String email
    ) {
        this.email = email;
    }

    public Member(
            String nickname ,
            String sex ,
            String dormitoryName
    ) {
        this.nickname = nickname;
        this.sex = sex;
        this.dormitoryName = dormitoryName;
    }

    public Member(
            LivingPattern livingPattern
    ) {
        this.livingPattern = livingPattern;
    }
}
