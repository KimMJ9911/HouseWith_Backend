package HouseWith.hwf.web.Dormitory.Controller;

import HouseWith.hwf.DTO.Main.ArticleDTO;
import HouseWith.hwf.DTO.Article.JoinRequestDTO;
import HouseWith.hwf.DTO.Article.RoomKeywordDTO;
import HouseWith.hwf.domain.Article.Article;
import HouseWith.hwf.domain.Article.ArticleRepository;
import HouseWith.hwf.web.Dormitory.Service.DormitoryArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DormitoryArticleController {
    private final DormitoryArticleService dormitoryArticleService;
    private final ArticleRepository articleRepository;

    /**
     * @param articleDTO : articleId , createdTime(수정된 시간으로 변경) , dormitory , title , quarter , access_max , comment , open_url
     * @param roomKeywordDTO : dormitory , motion , smoke , sleep_time , available_eat
     * @param joinRequestDTO : 방을 생성한 사람에게 OWNER 부여 , 방 소유자는 다른 방 가입 못하게 제거
     * @return : 방이 정상적으로 생성됨
     *
     * 7/9 - 수정 사항
     * 중복된 인원이 방을 하나 더 생성하는 경우 예외처리 (REQUEST_OVERFLOW : 방을 한 개 이상 생성하였습니다.)
     *
     */
    @Operation(
            summary = "방 생성 로직" ,
            description = """
                            방 생성에 필요한 정보들을 받아 저장하는 로직입니다.\s
                            방 생성 후 중복 인원 여부 판별 후 한 사람이 글을 여러개 쓰거나 이미 다른 방에 가입된 경우 글을 작성하지 못하도록 구성했습니다.\s
                            수정 사항 : 추후에 Token 으로 받아올 예정이니 ownerId 파라미터가 토큰이라고 생각하시면 됩니다.
                            """
    )
    @PostMapping(value = "createRoom" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> CreateRoom(
            @RequestPart("ownerId") String ownerId,
            @RequestPart("article") ArticleDTO articleDTO ,
            @RequestPart("roomKeyword") RoomKeywordDTO roomKeywordDTO ,
            @RequestPart("join") JoinRequestDTO joinRequestDTO) {
        System.out.println(ownerId);
        System.out.println(articleDTO);
        System.out.println(roomKeywordDTO);
        System.out.println(joinRequestDTO);

        Article newRoom = dormitoryArticleService.createRoom(
                Long.parseLong(ownerId) ,
                articleDTO ,
                roomKeywordDTO ,
                joinRequestDTO
        );
        articleRepository.save(newRoom);
        return ResponseEntity.ok("저장 완료");
    }




    /**
     * 6/25 -
     * @param articleDTO -> articleId , createdTime(수정된 시간으로 변경) , dormitory , title , quarter , access_max , comment , open_url
     * @param roomKeywordDTO -> dormitory , motion , smoke , sleep_time , available_eat
     * 6/28 - 테스트 완료
     */

    @PostMapping(value = "modifyRoom" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> modifyRoom
            (@RequestPart("memberId") String memberId ,
            @RequestPart("articleId") String articleId ,
            @RequestPart("article") ArticleDTO articleDTO,
            @RequestPart("roomKeyword") RoomKeywordDTO roomKeywordDTO) {
        Article newRoom = dormitoryArticleService.modifyRoom(
                Long.parseLong(memberId) ,
                Long.parseLong(articleId) ,
                articleDTO ,
                roomKeywordDTO
        );
        articleRepository.save(newRoom);

        return ResponseEntity.ok().build();
    }

    /**
     * @param articleId : 방 id 조회용
     * @return : articleId 를 이용해 방 검색 후 삭제
     * 7/6 - 테스트 완료
     */
    @Operation(summary = "글 삭제용 로직" , description = "수정 계획 : 권한 판단 후 삭제 가능 여부 추가할 예정")
    @DeleteMapping("deleteRoom/{articleId}")
    @Transactional
    public ResponseEntity<Void> deleteRoom(@PathVariable @RequestParam Long articleId) {
        articleRepository.deleteById(articleId);
        return ResponseEntity.ok().build();
    }
}
