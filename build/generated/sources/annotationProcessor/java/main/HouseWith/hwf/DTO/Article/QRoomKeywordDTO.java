package HouseWith.hwf.DTO.Article;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * HouseWith.hwf.DTO.Article.QRoomKeywordDTO is a Querydsl Projection type for RoomKeywordDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRoomKeywordDTO extends ConstructorExpression<RoomKeywordDTO> {

    private static final long serialVersionUID = -296299049L;

    public QRoomKeywordDTO(com.querydsl.core.types.Expression<String> dormitory, com.querydsl.core.types.Expression<String> motion, com.querydsl.core.types.Expression<String> smoke, com.querydsl.core.types.Expression<String> sleep_time, com.querydsl.core.types.Expression<String> available_eat) {
        super(RoomKeywordDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class}, dormitory, motion, smoke, sleep_time, available_eat);
    }

}

