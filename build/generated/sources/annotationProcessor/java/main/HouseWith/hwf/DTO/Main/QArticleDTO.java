package HouseWith.hwf.DTO.Main;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * HouseWith.hwf.DTO.Main.QArticleDTO is a Querydsl Projection type for ArticleDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QArticleDTO extends ConstructorExpression<ArticleDTO> {

    private static final long serialVersionUID = -1548887824L;

    public QArticleDTO(com.querydsl.core.types.Expression<Long> articleId, com.querydsl.core.types.Expression<String> owner_nickname, com.querydsl.core.types.Expression<Long> owner, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdTime, com.querydsl.core.types.Expression<String> dormitory, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> quarter, com.querydsl.core.types.Expression<Integer> join_member_count, com.querydsl.core.types.Expression<Integer> access_max, com.querydsl.core.types.Expression<String> comment, com.querydsl.core.types.Expression<String> open_url) {
        super(ArticleDTO.class, new Class<?>[]{long.class, String.class, long.class, java.time.LocalDateTime.class, String.class, String.class, String.class, int.class, int.class, String.class, String.class}, articleId, owner_nickname, owner, createdTime, dormitory, title, quarter, join_member_count, access_max, comment, open_url);
    }

}

