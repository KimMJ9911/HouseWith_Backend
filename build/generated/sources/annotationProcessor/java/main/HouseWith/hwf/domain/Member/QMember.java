package HouseWith.hwf.domain.Member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -913042909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final StringPath dormitoryName = createString("dormitoryName");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction_comment = createString("introduction_comment");

    public final ListPath<HouseWith.hwf.domain.JoinRequest.JoinRequest, HouseWith.hwf.domain.JoinRequest.QJoinRequest> joinRequests = this.<HouseWith.hwf.domain.JoinRequest.JoinRequest, HouseWith.hwf.domain.JoinRequest.QJoinRequest>createList("joinRequests", HouseWith.hwf.domain.JoinRequest.JoinRequest.class, HouseWith.hwf.domain.JoinRequest.QJoinRequest.class, PathInits.DIRECT2);

    public final HouseWith.hwf.domain.LivingPattern.QLivingPattern livingPattern;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath sex = createString("sex");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.livingPattern = inits.isInitialized("livingPattern") ? new HouseWith.hwf.domain.LivingPattern.QLivingPattern(forProperty("livingPattern"), inits.get("livingPattern")) : null;
    }

}

