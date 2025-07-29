package HouseWith.hwf.web.Login.Info.InitController;

import HouseWith.hwf.DTO.MyPage.LivingPatternDTO;
import HouseWith.hwf.domain.Member.Member;
import HouseWith.hwf.domain.Member.MemberRepository;
import HouseWith.hwf.web.Login.Info.Service.InfoService.InfoService;
import HouseWith.hwf.web.Login.Security.JWT.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user-info")
public class InitController {
    private final MemberRepository memberRepository;
    private final InfoService infoService;
    private final JwtUtil jwtUtil;


    /**
     * 6/27 - 생성
     * @param YonseiEmail
     * @param nickname
     * @param sex
     * @param dormitoryName
     * @return
     *
     * 7/8 - 수정 완료 , 테스트 완료
     */
    @Operation(
            summary = "회원 가입로직"
    )
    @PostMapping("basicInfo")
    public String SignIn(
            @RequestParam(value = "email") String YonseiEmail,
            @RequestParam String nickname,
            @RequestParam String sex ,
            @RequestParam(value = "dormitory") String dormitoryName) {
        Member member = infoService.getInitData(
                "user1" , "password!" , YonseiEmail , nickname , sex , dormitoryName
        );

//        String jwt = jwtUtil.genToken(
//                member.getId() ,
//                member.getEmail(),
//                member.getNickname()
//        );

        memberRepository.save(member);
        return "userID :" + member.getId();
    }

    /**
     * 7/8 - 구현 완료
     * @param nickname : 중복 검사할 닉네임
     * @return : ok -> 중복 있음
     */
    @Operation(
            summary = "닉네임 중복 체크" ,
            description = "불건전한 닉네임 체크는 구현하지 않았습니다. 추후에 개발 예정입니다."
    )
    @GetMapping("nickDup")
    public String NickDup(
            @RequestParam String nickname
    ) {
        return infoService.nickDuplicateCheck(nickname) ?
               "DUPLICATED" : "NOT_DUPLICATED";
    }

    /**
     * 7/6 - 개발 완료
     * @param livingPatternDTO : 개인 생활 패턴을 받아오는 DTO
     * @return : 받아서 저장합니다. -> 수정 기능은 추후에 개발
     */
    @Operation(
            summary = "사용자 세부 키워드" ,
            description = """
                    사용자 키워드들을 적는 로직입니다. \n
                    마이 페이지 구현이 안되어있어 필수 기능으로 넣진 않고 멤버와 연결은 안했습니다. 추후에 수정할 생각입니다.
                    """
    )
    @PostMapping("patternInfo")
    public ResponseEntity<?> PatternInfo(
            @RequestBody LivingPatternDTO livingPatternDTO ,
            @RequestParam String memberId) {
        memberRepository
                .save(infoService.getLivingPattern(livingPatternDTO , Long.parseLong(memberId)));
        return ResponseEntity.ok().build();
    }
}
