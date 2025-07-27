package HouseWith.hwf.domain.Member;

import HouseWith.hwf.DTO.MyPage.MemberDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findAllRequestByArticleId(long articleId);
    List<Member> findAllMemberAtArticle(long articleId);
    void updateOverThreshold(LocalDateTime threshold);
    Member findByMemberId(long memberId);
    boolean existsByNickname(String nickname);
    MemberDTO getPersonalInfo(Long memberId);

}
