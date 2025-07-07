package HouseWith.hwf.DTO;

import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class JoinRequestDTO {
    private LocalDateTime localDateTime;
}
