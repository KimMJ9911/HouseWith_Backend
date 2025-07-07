package HouseWith.hwf.web.MainContext;

import HouseWith.hwf.DTO.ArticleDTO;
import HouseWith.hwf.DTO.MemberDTO;
import HouseWith.hwf.DTO.RoomKeywordDTO;
import HouseWith.hwf.domain.Article.ArticleRepositoryCustom;
import HouseWith.hwf.domain.Member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainContextService {
    private final ArticleRepositoryCustom articleRepository;

    /**
     * @return : 모든 글을 최신순으로 반환
     */
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findArticles();
    }

    /**
     * @param search_key : 검색창에 들어가는 키워드
     * @param roomKeywordDTO : 방 조건 검색 키워드
     * @param dormitory : 기숙사 검색 키워드
     * @return : 키워드들 바탕으로 검색한 방들 반환
     */
    public List<ArticleDTO> getArticleByKeywords(
            String search_key,
            RoomKeywordDTO roomKeywordDTO,
            String dormitory) {
        // DTO가 null일 경우 각 필드도 null로 세팅
        String motion = null;
        String smoke = null;
        String sleep_time = null;
        String available_at = null;

        if (roomKeywordDTO != null) {
            motion = roomKeywordDTO.getMotion();
            smoke = roomKeywordDTO.getSmoke();
            sleep_time = roomKeywordDTO.getSleep_time();
            available_at = roomKeywordDTO.getAvailable_eat();
        }

        return articleRepository.findArticleByKeywords(
                search_key,
                motion,
                smoke,
                sleep_time,
                available_at,
                dormitory
        );
    }


    /**
     * @param articleId : 글 식별자
     * @return : 해당 글에 있는 인원들 전부 반환
     */
    public List<Member> getArticleByArticleId(Long articleId) {
        return articleRepository.findMembersByArticleId(articleId);
    }
}
