package HouseWith.hwf.domain.Article;

import HouseWith.hwf.DTO.ArticleDTO;
import HouseWith.hwf.DTO.MemberDTO;
import HouseWith.hwf.domain.Member.Member;

import java.time.LocalDate;
import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleDTO> findArticles();
    Article findArticlesById(Long articleId);
    List<ArticleDTO> findArticleByKeywords(String search_key ,
                                           String motion ,
                                           String smoke ,
                                           String sleep_time ,
                                           String available_at ,
                                           String dormitory);
    List<Member> findMembersByArticleId(Long articleId);
    //관리자 페이지용
    List<ArticleDTO> findArticleByTime_Admin(LocalDate localDate);
}
