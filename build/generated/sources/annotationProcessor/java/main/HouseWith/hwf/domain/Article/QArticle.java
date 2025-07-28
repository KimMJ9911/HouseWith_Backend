package HouseWith.hwf.domain.Article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -1369990093L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final NumberPath<Integer> access_max = createNumber("access_max", Integer.class);

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final StringPath dormitory = createString("dormitory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> join_member_count = createNumber("join_member_count", Integer.class);

    public final ListPath<HouseWith.hwf.domain.JoinRequest.JoinRequest, HouseWith.hwf.domain.JoinRequest.QJoinRequest> joinRequests = this.<HouseWith.hwf.domain.JoinRequest.JoinRequest, HouseWith.hwf.domain.JoinRequest.QJoinRequest>createList("joinRequests", HouseWith.hwf.domain.JoinRequest.JoinRequest.class, HouseWith.hwf.domain.JoinRequest.QJoinRequest.class, PathInits.DIRECT2);

    public final StringPath open_url = createString("open_url");

    public final NumberPath<Long> owner = createNumber("owner", Long.class);

    public final StringPath owner_nickname = createString("owner_nickname");

    public final StringPath quarter = createString("quarter");

    public final HouseWith.hwf.domain.Keywords.QRoomKeyword roomKeyword;

    public final StringPath title = createString("title");

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.roomKeyword = inits.isInitialized("roomKeyword") ? new HouseWith.hwf.domain.Keywords.QRoomKeyword(forProperty("roomKeyword"), inits.get("roomKeyword")) : null;
    }

}

