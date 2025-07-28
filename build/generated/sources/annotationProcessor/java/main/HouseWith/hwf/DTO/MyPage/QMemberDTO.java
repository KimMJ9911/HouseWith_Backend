package HouseWith.hwf.DTO.MyPage;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * HouseWith.hwf.DTO.MyPage.QMemberDTO is a Querydsl Projection type for MemberDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberDTO extends ConstructorExpression<MemberDTO> {

    private static final long serialVersionUID = -1600845120L;

    public QMemberDTO(com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> introduction_comment, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> sex, com.querydsl.core.types.Expression<String> dormitoryName, com.querydsl.core.types.Expression<? extends HouseWith.hwf.domain.LivingPattern.LivingPattern> livingPattern) {
        super(MemberDTO.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, HouseWith.hwf.domain.LivingPattern.LivingPattern.class}, memberId, name, introduction_comment, phone, email, nickname, sex, dormitoryName, livingPattern);
    }

}

