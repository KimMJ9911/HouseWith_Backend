package HouseWith.hwf.web.JoinMate.Service;

import HouseWith.hwf.domain.Member.Member;
import HouseWith.hwf.domain.Member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final MemberRepository memberRepository;

    public List<Member> getRequest_All(Long articleId) {
        return memberRepository
                .findAllRequestByArticleId(articleId);
    }

    /**
     * 컨트롤러에 @Scheduled , @Transactional 붙이지 말기 -> 컨트롤러는 http 요청 처리용이기 때문
     */
    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void deleteExpiredRequests() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(2);
        memberRepository
                .deleteOverThreshold(threshold);
    }
}
