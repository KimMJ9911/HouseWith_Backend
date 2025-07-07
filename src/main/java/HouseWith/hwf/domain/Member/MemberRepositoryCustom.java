package HouseWith.hwf.domain.Member;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findAllRequestByArticleId(Long articleId);
    void deleteOverThreshold(LocalDateTime threshold);
}
