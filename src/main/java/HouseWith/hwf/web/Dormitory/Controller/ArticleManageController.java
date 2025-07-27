package HouseWith.hwf.web.Dormitory.Controller;

import HouseWith.hwf.DTO.Main.ArticleDTO;
import HouseWith.hwf.DTO.Article.RoomKeywordDTO;
import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import HouseWith.hwf.web.Dormitory.Service.DormitoryArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("management")
@Tag(name = "관리 페이지" , description = "사용자가 속해있는 방에 대한 관리 페이지 입니다.ㅇㅈ")
public class ArticleManageController {
    private final DormitoryArticleService dormitoryArticleService;

    @Operation(
            summary = "관리 페이지에서 수정하는 로직입니다.",
            description = """
                    관리 페이지에서 수정하는 로직입니다.\s
                    7/13 일 기준 수정 : 해당 페이지 별 별도 기능으로 추가했습니다.\s
                    수정 계획 : 소속원 방출 기능을 추가할 예정입니다."""
    )
    @PostMapping(value = "modify" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> modifyArticle( @RequestPart("memberId") String memberId ,
                                          @RequestPart("articleId") String articleId ,
                                          @RequestPart("article") ArticleDTO articleDTO,
                                           @RequestPart("roomKeyword") RoomKeywordDTO roomKeywordDTO) {
        dormitoryArticleService.modifyRoom(
                Long.parseLong(memberId) ,
                Long.parseLong(articleId) ,
                articleDTO ,
                roomKeywordDTO
        );

        return ResponseEntity.ok().build();
    }

    /**
     * 7/8 - 생성 완료 /
     * @param articleId : 방 검색을 위한 ID
     * @param memberId : 해당 방에 속한 인원 검색을 위한 ID
     * @return : 검색 후 해당 방에 속한 인원의 JoinStatus 를 반환
     */
    @Operation(
            summary = "사용자 권한 제공 로직" ,
            description = "해당 방에서의 사용자의 권한을 반환하는 로직입니다."
    )
    @GetMapping("room/status")
    public JoinStatus getJoinStatus(
            @RequestParam Long articleId ,
            @RequestParam Long memberId) {
        return dormitoryArticleService.getStatus(articleId , memberId);
    }
}
