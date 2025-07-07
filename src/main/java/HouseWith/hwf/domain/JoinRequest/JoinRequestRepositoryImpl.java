package HouseWith.hwf.domain.JoinRequest;

import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import HouseWith.hwf.domain.Member.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

import static HouseWith.hwf.domain.JoinRequest.QJoinRequest.joinRequest;
import static HouseWith.hwf.domain.Member.QMember.member;

@RequiredArgsConstructor
public class JoinRequestRepositoryImpl implements JoinRequestRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @PersistenceContext
    private EntityManager em;

}
