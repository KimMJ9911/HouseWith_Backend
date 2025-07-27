package HouseWith.hwf.DTO.Article;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomKeywordDTO {
    private String dormitory;
    private String motion;
    private String smoke;
    private String sleep_time;
    private String available_eat;

    @QueryProjection
    public RoomKeywordDTO(
            String dormitory ,
            String motion,
            String smoke,
            String sleep_time,
            String available_eat) {
        this.dormitory = dormitory;
        this.motion = motion;
        this.smoke = smoke;
        this.sleep_time = sleep_time;
        this.available_eat = available_eat;
    }
}
