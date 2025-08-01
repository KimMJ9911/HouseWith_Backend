package HouseWith.hwf.domain.Keywords;

import HouseWith.hwf.DTO.Article.RoomKeywordDTO;
import HouseWith.hwf.domain.Article.Article;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomKeyword {
    @Id @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    //키워드 -> 중복 불가능하도록 설정
    private String dormitory;
    private String motion;
    private String smoke;
    private String sleep_time;
    private String available_eat;

    public void update_detail(RoomKeywordDTO detail) {
        this.dormitory = detail.getDormitory();
        this.motion = detail.getMotion();
        this.smoke = detail.getSmoke();
        this.sleep_time = detail.getSleep_time();
        this.available_eat = detail.getAvailable_eat();
    }

    public RoomKeyword(String dormitory ,
                       String motion,
                       String smoke,
                       String sleep_time,
                       String available_eat) {
        this.dormitory = dormitory;
        this.motion = motion;
        this.smoke = smoke;
        this.sleep_time = sleep_time;
        this.available_eat = available_eat;
    }

    public void set_Article(Article article) {
        this.article = article;
    }


}
