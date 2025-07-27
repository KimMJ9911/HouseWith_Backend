package HouseWith.hwf.domain.Article;


import HouseWith.hwf.DTO.Main.ArticleDTO;
import HouseWith.hwf.DTO.Article.DormitoryDTO;
import HouseWith.hwf.DTO.Main.QArticleDTO;
import HouseWith.hwf.DTO.MyPage.MemberDTO;
import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static HouseWith.hwf.domain.Article.QArticle.article;
import static HouseWith.hwf.domain.JoinRequest.QJoinRequest.joinRequest;
import static HouseWith.hwf.domain.Keywords.QRoomKeyword.roomKeyword;
import static HouseWith.hwf.domain.Member.QMember.member;
import static com.querydsl.core.types.Projections.list;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    /**
     * 전체 게시물 찾기
     * 25/6/23 - 업로드 시간 별로 저장 , 1 페이지 당 12 게시물
     *
     * 7/14 - 수정
     * Pageable 로 무한 스크롤 구현
     *
     * // 수정 필요 //
     * 생성시간 기준 -> 수정 시간 기준으로 변경 필요
     */

    @Override
    public List<ArticleDTO> findArticles() {
        return queryFactory
                .select(new QArticleDTO(
                        article.id ,
                        article.owner_nickname,
                        article.owner ,
                        article.createdTime ,
                        article.dormitory ,
                        article.title ,
                        article.quarter ,
                        article.join_member_count ,
                        article.access_max ,
                        article.comment ,
                        article.open_url))
                .from(article)
                .orderBy(article.createdTime.desc() , article.id.desc())
                .fetch();
    }

//    static BooleanExpression lastIdCondition(LocalDateTime lastCreatedTime , Long lastId) {
//        if (lastCreatedTime == null || lastId == null) return null;
//
//        return article.createdTime.lt(lastCreatedTime)
//                .or(article.id.lt(lastId)).and(article.createdTime.eq(lastCreatedTime));
//    }

    /**
     * 6/26 -
     * article_Id 로 게시글 검색
     *
     * 7/8 - 수정
     * articleId로 해당 방의 정보들 전체 반환
     * 방에 속한 member 들 전부 반환
     *
     * DTO 에서의 N + 1 문제 해결을 위해서 groupBy 와 Transform 을 사용한다.
     * DTO 에서 다대다 관계나 다대일 관계 , 일대일 관계를 포함한 경우 발생한다
     * transform -> 데이터를 in-memory 형식으로 flat 하게 받아오는 방법 이용
     */
    @Override
    public Optional<DormitoryDTO> findArticleAndAcceptedMember(Long articleId) {
        Map<Long , DormitoryDTO> result = queryFactory
                .from(article)
                .leftJoin(article.joinRequests , joinRequest)
                .leftJoin(joinRequest.member , member)
                .on(joinRequest.joinStatus.in(JoinStatus.ACCEPTED , JoinStatus.OWNER))
                .where(article.id.eq(articleId))
                .transform(GroupBy.groupBy(article.id).as(
                        Projections.constructor(DormitoryDTO.class ,
                                article.id ,
                                article.createdTime ,
                                article.dormitory ,
                                article.title ,
                                article.quarter ,
                                article.access_max ,
                                article.comment ,
                                article.open_url ,
                                list(
                                        Projections.constructor(MemberDTO.class ,
                                                member.id ,
                                                member.name ,
                                                member.introduction_comment ,
                                                member.phone ,
                                                member.email ,
                                                member.nickname ,
                                                member.sex ,
                                                member.dormitoryName ,
                                                member.livingPattern).skipNulls()
                                ))
                ));

        DormitoryDTO dto = result.get(articleId);

        if (dto == null) return Optional.empty();
        else return Optional.of(dto);
    }

    /**
     *
     * title_param -> 검색 키워드
     * 6/23 -
     * 검색 기능 추가 -> 비슷한 단어/키워드 검색
     * 페이징 기능 추가로 한 화면당 10개 -> 수정 가능
     * 인원 수 , 룸메이트 생활조건(중복)
     *
     */
    @Override
    public List<ArticleDTO> findArticleByKeywords(String search_key ,
                                                  String motion ,
                                                  String smoke ,
                                                  String sleep_time ,
                                                  String available_at ,
                                                  String dormitory) {
        return queryFactory
                .select(new QArticleDTO(
                        article.id ,
                        article.owner_nickname ,
                        article.owner ,
                        article.createdTime ,
                        article.dormitory ,
                        article.title ,
                        article.quarter ,
                        article.join_member_count ,
                        article.access_max ,
                        article.comment ,
                        article.open_url))
                .from(article)
                .leftJoin(article.roomKeyword , roomKeyword)
                .where(
                        searchKeyCondition(search_key) ,
                        motionEq(motion) ,
                        smokeEq(smoke) ,
                        sleep_timeEq(sleep_time) ,
                        available_atEq(available_at) ,
                        dormitoryEq(dormitory)
                        )
                .fetch();
    }


    /**
     * 6/25 -
     * 검색 키워드 추가 - 검색창
     *
     * 7/4 - 수정 완료
     * 검색창에서 기숙사 이름 검색을 제외했습니다.
     */
    private BooleanExpression searchKeyCondition(String search_key) {
        if (search_key == null || search_key.trim().isEmpty()) return null;

        String keyword = "%" + search_key.trim() + "%";
        return article.title.like(keyword).or(article.comment.like(keyword));
    }


    /**
     * 6/25 -
     * 검색 조건 키워드 추가 - 버튼
     *
     * 7/13 - 수정
     * 검색 키워드에 기숙사 이름도 추가
     */

    private BooleanExpression motionEq(String motion) {
        return motion != null ? roomKeyword.motion.eq(motion) : null;
    }

    private BooleanExpression smokeEq(String smoke) {
        return smoke != null ? roomKeyword.smoke.eq(smoke) : null;
    }

    private BooleanExpression sleep_timeEq(String sleep_time) {
        return sleep_time != null ? roomKeyword.sleep_time.eq(sleep_time) : null;
    }

    private BooleanExpression available_atEq(String available_eat) {
        return available_eat != null ? roomKeyword.available_eat.eq(available_eat) : null;
    }

    private BooleanExpression dormitoryEq(String dormitory) {
        return dormitory != null ? article.dormitory.eq(dormitory) : null;
    }


    public Article findArticlesById(Long articleId) {
        return queryFactory
                .selectFrom(article)
                .where(article.id.eq(articleId))
                .fetchOne();
    }


    /**
     * @param articleId : 방 id
     * @param memberId : 멤버 id
     * @return : 권한 반환
     */
    @Override
    public JoinStatus findJoinStatus(Long articleId , Long memberId) {
        return queryFactory
                .select(joinRequest.joinStatus)
                .from(joinRequest)
                .join(joinRequest.member , member)
                .join(joinRequest.article , article)
                .where(article.id.eq(articleId) ,
                        member.id.eq(memberId))
                .fetchOne();
    }

    public Long countArticlesByMember(Long memberId) {
        Long cnt = queryFactory
                .select(joinRequest.article.countDistinct())
                .from(joinRequest)
                .join(joinRequest.member , member)
                .where(joinRequest.member.id.eq(memberId) ,
                        joinRequest.joinStatus.in(JoinStatus.OWNER , JoinStatus.ACCEPTED))
                .fetchOne();
        return cnt == null ? 0L : cnt;
    }

    /**
     *
     * 6/26 -
     * 관리자 페이지 용 데이터 정보창
     * 날짜 별 생성된 방 갯수 + 성사된 매칭 수 + (가입 유저 수는 나중에 개발하는걸로)
     *
     */

    @Override
    public List<ArticleDTO> findArticleByTime_Admin(LocalDate localDate) {
        return queryFactory
                .select(new QArticleDTO(
                        article.id ,
                        article.owner_nickname ,
                        article.owner ,
                        article.createdTime ,
                        article.dormitory ,
                        article.title ,
                        article.quarter ,
                        article.join_member_count ,
                        article.access_max ,
                        article.comment ,
                        article.open_url))
                .from(article)
                .where(article.createdTime.year().eq(localDate.getYear())
                        .and(article.createdTime.month().eq(localDate.getMonthValue()))
                        .and(article.createdTime.dayOfMonth().eq(localDate.getDayOfMonth())))
                .fetch();
    }
}
