package HouseWith.hwf.domain.Keywords;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoomKeyword is a Querydsl query type for RoomKeyword
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoomKeyword extends EntityPathBase<RoomKeyword> {

    private static final long serialVersionUID = -189618955L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoomKeyword roomKeyword = new QRoomKeyword("roomKeyword");

    public final HouseWith.hwf.domain.Article.QArticle article;

    public final StringPath available_eat = createString("available_eat");

    public final StringPath dormitory = createString("dormitory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath motion = createString("motion");

    public final StringPath sleep_time = createString("sleep_time");

    public final StringPath smoke = createString("smoke");

    public QRoomKeyword(String variable) {
        this(RoomKeyword.class, forVariable(variable), INITS);
    }

    public QRoomKeyword(Path<? extends RoomKeyword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoomKeyword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoomKeyword(PathMetadata metadata, PathInits inits) {
        this(RoomKeyword.class, metadata, inits);
    }

    public QRoomKeyword(Class<? extends RoomKeyword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.article = inits.isInitialized("article") ? new HouseWith.hwf.domain.Article.QArticle(forProperty("article"), inits.get("article")) : null;
    }

}

