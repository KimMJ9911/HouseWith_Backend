package HouseWith.hwf.web.JoinMate.Controller;

import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import HouseWith.hwf.domain.Member.Member;
import HouseWith.hwf.web.JoinMate.Service.JoinMateService;
import HouseWith.hwf.web.JoinMate.Service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("mate")
public class JoinController {
    private final JoinMateService joinMateService;
    private final OwnerService ownerService;

    /**
     * 6/26 -
     * 가입한 멤버들의 목록을 받아오는 로직
     * @param articleId :
     * @return : 어떤 값을 반환할지 아직 미정
     */
    @Operation(
            summary = "모든 요청들을 받아오는 로직" ,
            description = """
                         해당 방에 온 요청들 (가입 신청 등) 을 전부 받아오는 로직입니다.\s
                         WAITING 인 사람들만 모두 받아오도록 구현했습니다.
                         """
    )
    @GetMapping("requests")
    public List<Member> getRequests(@RequestParam String articleId) {

//        List<String> name = new ArrayList<>();
//        for (Member member : members) {
//            name.add(member.getName());
//        }
//        if (name.isEmpty())
//            throw new IllegalParamException("NULL");

        return ownerService.getRequest_All(Long.parseLong(articleId));
    }

    /**
     * 6/26 -
     * 원하는 방에 신청하여 WAITING 상태로 방에 소속
     * @param articleId : 글 목록 반환
     * @param memberId : 가입한 멤버
     * @return : 방에 가입 신청
     *
     * 7/8 - 수정 완료
     * 잘못된 쿼리 방향성 잡기
     * 비효율적인 엔티티 및 관계 수정 완료
     *
     * 7/9 - 수정 완료
     * 가입 인원 수 + 1 구현 필요
     * joinRequest 를 추가하기
     *
     * 7/13 - 수정 완료
     * 가입 인원 수 + 1 구현 완료
     */
    @Operation(
            summary = "가입 신청 로직" ,
            description = """
                    방에 가입하는 로직입니다.\s
                    방에 가입하는 경우 중복 가입 여부를 판별합니다. 중복 가입은 불가능합니다.\s
                    인원 초과하는 경우 가입 불가능 하도록 구현하였습니다.\s
                    방에 신청했던 모든 인원들은 재가입이 불가능하게 구현했습니다.(거절 포함)
                    """
    )
    @PostMapping("joinMember")
    public ResponseEntity<?> joinMember(@RequestParam Long articleId ,
                                         @RequestParam Long memberId) {
        joinMateService.ToJoin(articleId , memberId);
        return ResponseEntity.ok("신청 완료");
    }


    /**
     * 7/1 - 구현 완료
     * 가입 수락 / 거절 버튼
     * 가입 희망자 ID , 방 ID 받아오기
     *
     * 7/9 - 수정 완료 , 테스트 완료
     * 예외 처리 완료
     * 예외 처리구문 구현 완료
     */
    @Operation(
            summary = "가입 수락" ,
            description = """
                    가입 수락 로직입니다.\s
                    가입 신청 시 중복 방 생성이나 가입은 허용되지 않았으므로 수락 로직에 구현하지 않았습니다.
                    """
    )
    @PostMapping("requests/accept")
    public ResponseEntity<?> acceptMember(
            @RequestParam Long articleId,
            @RequestParam Long memberId) {
        ownerService.Request_To_Join(articleId , memberId , JoinStatus.ACCEPTED);

        return ResponseEntity.ok("가입 수락 완료");
    }
    @Operation(
            summary = "가입 거절" ,
            description = """
                    가입 거절 로직입니다.\s
                    가입 신청 시 중복 방 생성이나 가입은 허용되지 않았으므로 거절 로직에 구현하지 않았습니다.
                    """
    )
    @PostMapping("requests/reject")
    public ResponseEntity<?> rejectMember(
            @RequestParam Long articleId,
            @RequestParam Long memberId) {
        ownerService.Request_To_Join(articleId , memberId , JoinStatus.REJECTED);

        return ResponseEntity.ok("가입 거절 완료");
    }

    /**
     * 7/1 - 구현 완료
     * 매일 새벽 4시에 초기화
     * Transactional 사용해서 cronTab 이 자동적으로 수행 하도록 구성
     *
     * 테스트 하기 힘들어서 이건 보류
     */
    @Operation(
            summary = "요청 삭제 로직" ,
            description = """
                    신청 삭제 로직입니다.\s
                    가입 신청 중 WAITING 은 만료일을 2일 두고 자동 삭제됩니다.\s
                    가입 신청 중 REJECTED 역시 2일 뒤에 삭제됩니다. 거절당한 인원은 2일 뒤에 재신청이 가능합니다.
                    """
    )
    @DeleteMapping
    public void expiration() {
        ownerService.deleteExpiredRequests();
    }

}
