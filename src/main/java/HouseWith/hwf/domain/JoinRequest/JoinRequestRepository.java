package HouseWith.hwf.domain.JoinRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> , JoinRequestRepositoryCustom {
}
