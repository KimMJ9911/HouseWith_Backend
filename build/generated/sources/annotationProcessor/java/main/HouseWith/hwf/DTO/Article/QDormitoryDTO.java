package HouseWith.hwf.DTO.Article;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * HouseWith.hwf.DTO.Article.QDormitoryDTO is a Querydsl Projection type for DormitoryDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QDormitoryDTO extends ConstructorExpression<DormitoryDTO> {

    private static final long serialVersionUID = -1363351840L;

    public QDormitoryDTO(com.querydsl.core.types.Expression<Long> articleId, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdTime, com.querydsl.core.types.Expression<String> dormitory, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> quarter, com.querydsl.core.types.Expression<Integer> access_max, com.querydsl.core.types.Expression<String> comment, com.querydsl.core.types.Expression<String> open_url, com.querydsl.core.types.Expression<? extends java.util.List<HouseWith.hwf.DTO.MyPage.MemberDTO>> memberDTO) {
        super(DormitoryDTO.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, String.class, String.class, String.class, int.class, String.class, String.class, java.util.List.class}, articleId, createdTime, dormitory, title, quarter, access_max, comment, open_url, memberDTO);
    }

}

