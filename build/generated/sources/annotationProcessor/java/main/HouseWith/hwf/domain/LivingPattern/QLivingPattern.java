package HouseWith.hwf.domain.LivingPattern;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLivingPattern is a Querydsl query type for LivingPattern
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLivingPattern extends EntityPathBase<LivingPattern> {

    private static final long serialVersionUID = -1627597739L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLivingPattern livingPattern = new QLivingPattern("livingPattern");

    public final StringPath available_eat = createString("available_eat");

    public final StringPath call_pattern = createString("call_pattern");

    public final StringPath home_leaving = createString("home_leaving");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introvert = createString("introvert");

    public final HouseWith.hwf.domain.Member.QMember member;

    public final StringPath night_work = createString("night_work");

    public final StringPath sanitary = createString("sanitary");

    public final StringPath sharing = createString("sharing");

    public final StringPath shower_pattern = createString("shower_pattern");

    public final StringPath sleep_pattern = createString("sleep_pattern");

    public final StringPath smoke = createString("smoke");

    public final StringPath snoring = createString("snoring");

    public final StringPath speaker_use = createString("speaker_use");

    public QLivingPattern(String variable) {
        this(LivingPattern.class, forVariable(variable), INITS);
    }

    public QLivingPattern(Path<? extends LivingPattern> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLivingPattern(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLivingPattern(PathMetadata metadata, PathInits inits) {
        this(LivingPattern.class, metadata, inits);
    }

    public QLivingPattern(Class<? extends LivingPattern> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new HouseWith.hwf.domain.Member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

