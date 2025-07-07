package HouseWith.hwf.web.JoinMate.Controller;

import HouseWith.hwf.domain.JoinRequest.JoinRequest;
import HouseWith.hwf.domain.JoinRequest.JoinRequestRepository;
import HouseWith.hwf.domain.Member.Member;
import HouseWith.hwf.web.JoinMate.Service.JoinMateService;
import HouseWith.hwf.web.JoinMate.Service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("mate")
public class JoinController {
    private final JoinMateService joinMateService;
    private final OwnerService ownerService;
    private final JoinRequestRepository joinRequestRepository;

    /**
     * 6/26 -
     * 가입한 멤버들의 목록을 받아오는 로직
     * @param articleId
     * @return
     */
    @GetMapping("requests")
    public List<Member> getRequests(@RequestParam Long articleId) {
        return ownerService.getRequest_All(articleId);
    }

    /**
     * 6/26 -
     * 원하는 방에 신청하여 WAITING 상태로 방에 소속
     * @param articleId : 글 목록 반환
     * @param member : 가입한 멤버
     * @return
     */
    @PostMapping("joinMember")
    public ResponseEntity<?> joinMember(@RequestParam Long articleId,
                                        @RequestBody Member member) {
        JoinRequest join = joinMateService.ToJoin(articleId , member);
        joinRequestRepository.save(join);

        return ResponseEntity.ok("신청 완료");
    }

    /**
     * 가입 수락 / 거절 버튼
     * @param joinRequest
     */
    @PostMapping("requests/accept")
    public void acceptMember(@RequestBody JoinRequest joinRequest) {
        joinMateService.Accept_member(joinRequest);
    }

    @PostMapping("requests/reject")
    public void rejectMember(@RequestBody JoinRequest joinRequest) {
        joinMateService.Reject_member(joinRequest);
    }

    /**
     *
     * 매일 새벽 4시에 초기화
     *
     */

    public void expiration() {
        ownerService.deleteExpiredRequests();
    }

}
