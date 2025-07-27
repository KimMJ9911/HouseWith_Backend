package HouseWith.hwf.domain.Member;

import HouseWith.hwf.DTO.MyPage.MemberDTO;
import HouseWith.hwf.DTO.MyPage.QMemberDTO;
import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static HouseWith.hwf.domain.Article.QArticle.article;
import static HouseWith.hwf.domain.JoinRequest.QJoinRequest.joinRequest;
import static HouseWith.hwf.domain.LivingPattern.QLivingPattern.*;
import static HouseWith.hwf.domain.Member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory queryFactory;

    /**
     * 6/26 -
     * 해당 방에서 요청을 보낸 사람들 목록을 받아오는 로직
     * JoinStatus 가 WAITING 인 사람만 가능
     */
    @Override
    public List<Member> findAllRequestByArticleId(long articleId) {
        return queryFactory
                .select(joinRequest.member)
                .from(joinRequest)
                .join(joinRequest.article , article)
                .where(article.id.eq(articleId) ,
                        joinRequest.joinStatus.eq(JoinStatus.WAITING))
                .fetch();
    }

    /**
     * 7/13 - 개발 완료
     * @param articleId : 방 ID
     * @return : 방에 있는 모든 인원들(소유자 , 가입 , 거절 , 대기 인원 모두 받아옵니다.)
     */
    @Override
    public List<Member> findAllMemberAtArticle(long articleId) {
        return queryFactory
                .select(joinRequest.member)
                .from(joinRequest)
                .join(joinRequest.article , article)
                .where(article.id.eq(articleId))
                .fetch();
    }



    /**
     * 6/28 - 산청 후 WAITING , REJECTED 성탸인 사용자에 대한 만료 작업 ,크론탭을 이용한 자동화 작업 진행
     * @param threshold : 산청 후 WAITING , REJECTED 상태인 사용자에 대한 만료 시간
     */

    @Override
    public void updateOverThreshold(LocalDateTime threshold) {
        long deleteCount = queryFactory
                .update(joinRequest)
                .set(joinRequest.joinStatus, JoinStatus.NON)
                .where(
                        //in 은 or 조건으로 받아지고
                        joinRequest.joinStatus.in(JoinStatus.WAITING , JoinStatus.REJECTED) ,
                        //before 은 and 조건으로 받아진다
                        joinRequest.localDateTime.before(threshold)
                )
                .execute();
        em.flush();
        em.clear();
    }

    /**
     * 6/28 - 구현
     * @param memberId : memberId
     * @return : 멤버 반환
     */
    @Override
    public Member findByMemberId(long memberId) {
        return queryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }

    /**
     * 7/8 - 구현
     * @param nickname : 중복 검사할 닉네임
     * @return : 닉네임 중복 여부 boolean 으로 반환
     */
    @Override
    public boolean existsByNickname(String nickname) {
        return queryFactory
                .selectFrom(member)
                .where(member.nickname.eq(nickname))
                .fetchOne() != null;
    }

    @Override
    public MemberDTO getPersonalInfo(Long memberId) {
        return queryFactory
                .select(new QMemberDTO(
                        member.id ,
                        member.name ,
                        member.introduction_comment ,
                        member.phone ,
                        member.email ,
                        member.nickname ,
                        member.sex ,
                        member.dormitoryName ,
                        member.livingPattern
                ))
                .from(member)
                .leftJoin(member.livingPattern , livingPattern)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
