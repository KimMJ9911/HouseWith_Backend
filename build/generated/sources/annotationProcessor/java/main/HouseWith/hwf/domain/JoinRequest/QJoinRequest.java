package HouseWith.hwf.domain.JoinRequest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJoinRequest is a Querydsl query type for JoinRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJoinRequest extends EntityPathBase<JoinRequest> {

    private static final long serialVersionUID = -1591222575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJoinRequest joinRequest = new QJoinRequest("joinRequest");

    public final HouseWith.hwf.domain.Article.QArticle article;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus> joinStatus = createEnum("joinStatus", HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus.class);

    public final DateTimePath<java.time.LocalDateTime> localDateTime = createDateTime("localDateTime", java.time.LocalDateTime.class);

    public final HouseWith.hwf.domain.Member.QMember member;

    public QJoinRequest(String variable) {
        this(JoinRequest.class, forVariable(variable), INITS);
    }

    public QJoinRequest(Path<? extends JoinRequest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJoinRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJoinRequest(PathMetadata metadata, PathInits inits) {
        this(JoinRequest.class, metadata, inits);
    }

    public QJoinRequest(Class<? extends JoinRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.article = inits.isInitialized("article") ? new HouseWith.hwf.domain.Article.QArticle(forProperty("article"), inits.get("article")) : null;
        this.member = inits.isInitialized("member") ? new HouseWith.hwf.domain.Member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

