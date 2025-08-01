package HouseWith.hwf.DTO.Main;

import HouseWith.hwf.domain.Article.Article;
import com.querydsl.core.QueryResults;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePreviewDTO {
    private Long articleId;
    private String Times_Ago;
    //소유자 이름(닉네임)
    private String nickname;
    //소유자
    private Long owner;
    //기숙사 정보
    private String dormitory;
    //제목
    private String title;
    //학기
    private String quarter;
    //현재 인원
    private Integer join_member_count;
    //전체 인원
    private Integer access_max;
    //방 소개글
    private String comment;
}
