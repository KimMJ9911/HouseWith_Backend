package HouseWith.hwf.web.MainContext.Controller;

import HouseWith.hwf.DTO.Main.ArticlePreviewDTO;
import HouseWith.hwf.DTO.Article.DormitoryDTO;
import HouseWith.hwf.DTO.Article.RoomKeywordDTO;
import HouseWith.hwf.web.MainContext.Service.MainContextService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("dormitory")
@Tag(name = "메인 화면" , description = "메인 화면에서 제공해야 할 정보들을 전달하는 로직입니다.")
public class MainContextController {
    private final MainContextService articleService;
    private final MainContextService mainContextService;

    /**
     * 6/28 - 테스트 완료
     * 모든 글들을 desc 로 정렬하여 전달 (시간 순으로 정렬)
     *
     * 7/8 - 수정 완료
     * 리소스 낭비 최소화를 위해 별개 DTO 구성
     * 비 정상적이게 많이 쿼리를 날리는 부분이 있어 수정
     */
    @Operation(
            summary = "전체 리스트" ,
            description = """
                    전체 리스트들을 시간순으로 desc 반환한 것입니다.
                    글 전체의 리소스들을 반환하는 것은 비효율적이라 별개의 DTO 로 전달했습니다.
                    수정 사항 : 소유자 , 전체 인원 , 현재 인원들을 추가했습니다.
                    """
    )
    @GetMapping("list")
    public List<ArticlePreviewDTO> MainPage() {
        return articleService.getAllArticles();
    }

    /**
     * 7/8 - 생성 완료 / 테스트 완료
     * @param articleId : 방 검색 ID
     * @return : 방 세부 정보 반환 , 방 미리보기 영역을 누르면 나오는 디테일 화면
     */
    @Operation(
            summary = "방 조회 로직"
    )
    @GetMapping("room/{articleId}")
    public Optional<DormitoryDTO> DormitoryDetail(@PathVariable @RequestParam Long articleId) {
        return mainContextService
                .getArticleDetail(articleId);
    }

    /**
     * @param search_key -> 검색어 키워드
     * @param roomKeyword -> 방 검색 키워드들
     * @return -> List 형식으로 반환
     *
     * 6/29 : service 에서 null 이 가능하도록 변경 (null 상태를 넘겨받아도 변수처리 되도록)
     *        fetch join 빼기 -> DTO 패턴에선 fetch join 을 사용하지 않는다
     *        테스트 완료
     */
    @Operation(
            summary = "검색 로직" ,
            description = """
                    검색 로직을 구현했습니다.\s
                    검색 창은 title 과 comment 만 검색 가능하도록 구현했습니다. 검색창에 아무것도 없어도 검색 가능하도록 구현했습니다.\s
                    마찬가지로 각 키워드는 체크하지 않아도 검색할 수 있도록 구현했습니다.\s
                    수정 사항 : 소유자 검색도 추가해야 하나? 논의 필요
                    """
    )
    @PostMapping("/search")
    public List<ArticlePreviewDTO> search(@RequestParam(required = false) String search_key ,
                                   @RequestBody(required = false) RoomKeywordDTO roomKeyword) {
        return articleService.getArticleByKeywords(
                search_key ,
                roomKeyword
        );
    }
}
