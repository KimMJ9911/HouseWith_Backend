package HouseWith.hwf.web.Dormitory.Service;

import HouseWith.hwf.DTO.Main.ArticleDTO;
import HouseWith.hwf.DTO.Article.JoinRequestDTO;
import HouseWith.hwf.DTO.Article.RoomKeywordDTO;
import HouseWith.hwf.Exceptions.RequestExceptioons.ArticleNotFoundException;
import HouseWith.hwf.Exceptions.RequestExceptioons.IllegalJoinStatusException;
import HouseWith.hwf.domain.Article.Article;
import HouseWith.hwf.domain.Article.ArticleRepository;
import HouseWith.hwf.domain.JoinRequest.Custom.JoinStatus;
import HouseWith.hwf.domain.JoinRequest.JoinRequest;
import HouseWith.hwf.domain.JoinRequest.JoinRequestRepository;
import HouseWith.hwf.domain.Keywords.RoomKeyword;
import HouseWith.hwf.domain.Keywords.RoomKeywordRepository;
import HouseWith.hwf.domain.Member.Member;
import HouseWith.hwf.domain.Member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DormitoryArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final RoomKeywordRepository roomKeywordRepository;
    private final JoinRequestRepository joinRequestRepository;

    @Value("${ErrorCode.Param.INVALID_JOIN_STATUS}")
    private String error_INVALID_STATUS;

    @Value("${ErrorCode.Request.UNAVAILABLE_REQUEST_NULL}")
    private String error_NULL;

    @Value("${ErrorCode.Request.REQUEST_OVERFLOW}")
    private String error_OVERFLOW;

    static String ERROR = "잘못된 요청입니다. ERROR CODE : ";

    /**
     * @param ownerId : 중복 생성을 피하기 위한 파라미터
     * @param articleDTO : 새로 받은 방
     * @param roomKeywordDTO : 새로 받은 키워드
     * @param joinRequestDTO : 새로 받은 join
     * @return : 방 생성 이후 객체에 담아 반환
     *
     * 7/8 - 수정
     * 기존 비효율적인 객체 생성을 줄였고
     * 글 생성 갯수가 초과인 경우 방지 추가
     *
     * 7/8 - 테스트 완료
     *
     * 생성 완료 후 OWNER 의 ownerId 저장하지 않는 문제 수정 필요
     * -> 수정 완료
     * 생성한 사람의 memberId(ownerId) 를 받아오도록 변경
     *
     * 뭔가 잘못됐다 -> 방 생성 후 최대 1개까지만 작성 가능했는데 왜 중복 생성이 가능하지? 딱 2개까지만?
     * 수정해야 한다....
     */
    @Transactional
    public Article createRoom(
            Long ownerId ,
            ArticleDTO articleDTO ,
            RoomKeywordDTO roomKeywordDTO ,
            JoinRequestDTO joinRequestDTO) {

        /**
         * 해당 인물이 작성한 방의 갯수
         * 최대 1개까지 작성 가능하도록
         */
        long cnt = articleRepository
                .countArticlesByMember(ownerId);

        if (cnt >= 1)
            throw new IllegalJoinStatusException(ERROR + error_OVERFLOW + " : 방을 한 개 이상 생성 혹은 가입하였습니다.");

        Member member = memberRepository
                .findByMemberId(ownerId);
        /**
         * 새 방을 설정
         * 7/9 - 방 생성자의 ID 를 받아오는 장치 추가
         */
        Article newRoom = new Article(
                member.getNickname() ,
                ownerId ,
                articleDTO.getDormitory() ,
                articleDTO.getTitle() ,
                articleDTO.getQuarter() ,
                1 ,
                articleDTO.getAccess_max() ,
                articleDTO.getComment() ,
                articleDTO.getOpen_url()
        );

        RoomKeyword roomKeyword = new RoomKeyword(
                roomKeywordDTO.getDormitory() ,
                roomKeywordDTO.getMotion() ,
                roomKeywordDTO.getSmoke() ,
                roomKeywordDTO.getSleep_time() ,
                roomKeywordDTO.getAvailable_eat()
        );

        JoinRequest joinRequest = new JoinRequest(
                joinRequestDTO.getLocalDateTime() ,
                JoinStatus.OWNER
        );

        //생성한 방과 생성한 사람의 ID 를 저장
        joinRequest.set_Article(newRoom);
        joinRequest.set_Member(memberRepository.findByMemberId(ownerId));

        //키워드와 가입 설정까지 저장
        newRoom.set_roomKeyword(roomKeyword);
        newRoom.set_joinRequest(joinRequest);


        roomKeywordRepository.save(roomKeyword);
        joinRequestRepository.save(joinRequest);
        articleRepository.save(newRoom);

        return newRoom;
    }

    public Article modifyRoom(
            Long ownerId ,
            Long articleId ,
            ArticleDTO articleDTO ,
            RoomKeywordDTO roomKeywordDTO) {

        JoinStatus status =
                articleRepository.findJoinStatus(ownerId , articleId);

        if (status == null)
            throw new ArticleNotFoundException(ERROR + error_NULL);
        else if (!status.equals(JoinStatus.OWNER))
            throw new IllegalStateException(ERROR + error_INVALID_STATUS);

        Article newRoom =
                articleRepository.findArticlesById(ownerId);
        if (newRoom == null)
            throw new ArticleNotFoundException(ERROR + error_NULL);

        newRoom.update_detail(articleDTO);

        RoomKeyword roomKeyword = newRoom.getRoomKeyword();
        if (roomKeyword == null)
            throw new ArticleNotFoundException(ERROR + error_NULL);
        roomKeyword.update_detail(roomKeywordDTO);

        //키워드 입력
        newRoom.set_roomKeyword(roomKeyword);
        return newRoom;
    }

    /**
     * @param articleId : 방 검색용
     * @param memberId : 사용자 Id
     * @return : 사용자 ID 를 통해 사용자가 해당 방에서의 권한을 받아오는 로직
     */
    public JoinStatus getStatus(Long articleId , Long memberId) {
        return articleRepository.findJoinStatus(articleId , memberId);
    }
}
