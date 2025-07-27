package HouseWith.hwf.web.Login.Info.MyPageController;

import HouseWith.hwf.DTO.MyPage.MyPageDTO;
import HouseWith.hwf.DTO.MyPage.MyPageMainDTO;
import HouseWith.hwf.domain.Member.Member;
import HouseWith.hwf.domain.Member.MemberRepository;
import HouseWith.hwf.web.Login.Info.Service.InfoService.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("MyPage")
public class MyPageController {
    private final InfoService infoService;
    private final MemberRepository memberRepository;

    @Operation(
            summary = "마이페이지 첫 화면에 나오는 소개 전달 로직입니다." ,
            description = "마이 페이지 첫 화에서 멤버 닉네임과 짧은 소개문을 반환합니다."
    )
    @GetMapping("")
    public MyPageMainDTO MainPage(@RequestParam Long memberId) {
        Member member = memberRepository.findByMemberId(memberId);

        return new MyPageMainDTO(
                member.getNickname() ,
                member.getIntroduction_comment()
        );
    }

    @Operation(
            summary = "마이 페이지에서 내 정보들을 확인하는 로직입니다." ,
            description = """
                    내가 앱을 시작할 때 작성했던 기본 정보들과 생활 패턴들을 확인합니다.\s
                    초반 설정 때 짧을 소개문은 작성하지 않았으므로 수정 flow 를 겪지 않았다면 보이지 않는 것이 정상입니다.\s
                    member 객체에서 받았던 모든 정보를 전달하므로 불필요한 정보들이 전달될 수 있습니다.
                    """
    )
    @GetMapping("Info")
    public MyPageDTO mypage(@RequestParam Long memberId) {
        return infoService.getMyPageInfo(memberId);
    }

    @Operation(
            summary = "닉네임 중복 처리 기능입니다."
    )
    @GetMapping("Info/nicDup")
    public ResponseEntity<?> nicDup(@RequestParam String nickname) {
        return infoService.nickDuplicateCheck(nickname) ?
                ResponseEntity.ok("DUPLICATED") : ResponseEntity.ok("NOT_DUPLICATED");
    }

    @Operation(
            summary = "내 정보를 수정하는 로직입니다." ,
            description = """
                    내 개인 정보를 수정하는 로직입니다.\s
                    개인 정보 수정 후 바로 저장하지 못하고 넘기도록 수정했습니다.\s
                    닉네임이 기존과 같다면 바로 저장 , 아니라면 중복 체크 후 저장 / 반려\s
                    나머지 정보들은 바로 저장되도록 수정했습니다.
                    """
    )
    @PostMapping("Info/modify")
    public ResponseEntity<?> modifyMyPage(
            @RequestParam Long memberId,
            @RequestBody MyPageDTO myPageDTO) {
        infoService.modifyMyPage(memberId , myPageDTO);

        return ResponseEntity.ok().build();
    }
}
