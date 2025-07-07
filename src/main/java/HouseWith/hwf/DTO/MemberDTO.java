package HouseWith.hwf.DTO;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MemberDTO {
    private Long Id;
    private String name;
    private String phone;
    private String email;

    @QueryProjection
    public MemberDTO(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
