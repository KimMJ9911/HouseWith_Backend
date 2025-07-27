package HouseWith.hwf.domain.Article;

import HouseWith.hwf.DTO.Main.ArticleDTO;
import HouseWith.hwf.DTO.Article.DormitoryDTO;
import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryCustom {
    List<ArticleDTO> findArticles();
    Optional<DormitoryDTO> findArticleAndAcceptedMember(Long articleId);
    Article findArticlesById(Long articleId);
    List<ArticleDTO> findArticleByKeywords(String search_key ,
                                           String motion ,
                                           String smoke ,
                                           String sleep_time ,
                                           String available_at ,
                                           String dormitory);
    JoinStatus findJoinStatus(Long articleId , Long memberId);
    Long countArticlesByMember(Long memberId);
//    List<Member> findMembersByArticleId(Long articleId);
    //관리자 페이지용
    List<ArticleDTO> findArticleByTime_Admin(LocalDate localDate);
}
