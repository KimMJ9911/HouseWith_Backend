package HouseWith.hwf.DTO.Article;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class JoinRequestDTO {
    private LocalDateTime localDateTime;
}
