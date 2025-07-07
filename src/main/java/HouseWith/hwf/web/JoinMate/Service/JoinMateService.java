package HouseWith.hwf.web.JoinMate.Service;

import HouseWith.hwf.domain.Article.Article;
import HouseWith.hwf.domain.Article.ArticleRepository;
import HouseWith.hwf.domain.JoinRequest.JoinRequest;
import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import HouseWith.hwf.domain.Member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JoinMateService {
    private final ArticleRepository articleRepository;

    public JoinRequest ToJoin(Long articleId ,
                                   Member member) {
        Article joinRoom = articleRepository.findArticlesById(articleId);
        JoinRequest joinRequest = new JoinRequest(
                joinRoom ,
                member ,
                JoinStatus.WAITING);
        return joinRequest;
    }

    public JoinRequest Accept_member(JoinRequest joinRequest) {
        joinRequest.accept();
        return joinRequest;
    }

    public JoinRequest Reject_member(JoinRequest joinRequest) {
        joinRequest.reject();
        return joinRequest;
    }

}
