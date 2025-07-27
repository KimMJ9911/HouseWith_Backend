package HouseWith.hwf.web.Login.Info.Service.InfoService;

import HouseWith.hwf.DTO.MyPage.LivingPatternDTO;
import HouseWith.hwf.DTO.MyPage.MemberDTO;
import HouseWith.hwf.DTO.MyPage.MyPageDTO;
import HouseWith.hwf.Exceptions.RequestExceptioons.IllegalParamException;
import HouseWith.hwf.Exceptions.RequestExceptioons.MemberNotFoundException;
import HouseWith.hwf.domain.LivingPattern.LivingPattern;
import HouseWith.hwf.domain.LivingPattern.LivingPatternRepository;
import HouseWith.hwf.domain.Member.Member;
import HouseWith.hwf.domain.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoService {
    private final MemberRepository memberRepository;

    static String CHECK = "중복 확인 요청입니다.";
    private final LivingPatternRepository livingPatternRepository;


    public Member getInitData(
            String username,
            String password,
            String email ,
            String nickname ,
            String sex ,
            String dormitoryName) {

        if (memberRepository.existsByNickname(nickname))
            throw new IllegalParamException(CHECK);

        return new Member(
                username , password , email , nickname , sex , dormitoryName
        );
    }

    /**
     * 7/6 - 개발 완료
     * @param nickname : 중복 검사할 닉네임
     * @return : true = 중복된 닉네임 있음 / false = 중복된 닉네임 없음
     */
    public boolean nickDuplicateCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public Member getLivingPattern(LivingPatternDTO livingPatternDTO , Long memberId) {
        //새로운 LP 받아옴
        LivingPattern livingPattern = new LivingPattern(
                livingPatternDTO.getSleep_pattern() ,
                livingPatternDTO.getSnoring() ,
                livingPatternDTO.getNight_work() ,
                livingPatternDTO.getHome_leaving() ,
                livingPatternDTO.getShower_pattern() ,
                livingPatternDTO.getSharing() ,
                livingPatternDTO.getSpeaker_use() ,
                livingPatternDTO.getCall_pattern() ,
                livingPatternDTO.getIntrovert() ,
                livingPatternDTO.getSanitary() ,
                livingPatternDTO.getSmoke() ,
                livingPatternDTO.getAvailable_eat()
        );

        livingPatternRepository.save(livingPattern);

        //해당 멤버 조회하고 저장하기

        Member member =
                memberRepository.findByMemberId(memberId);

        member.poll_LP(livingPattern);

        return member;
    }

    /**
     * 7/19 - 개발 완료
     * @param memberId
     * @return
     */
    public MyPageDTO getMyPageInfo(Long memberId) {
        MemberDTO memberDTO = memberRepository.getPersonalInfo(memberId);
        return new MyPageDTO(memberDTO);
    }

    /**
     * 7/19 - 개발 완료
     * @param memberId
     * @return
     */
    public void modifyMyPage(Long memberId , MyPageDTO myPageDTO) {
        Member member = memberRepository.findByMemberId(memberId);

        //닉네임 중복검사 없이 저장 불가 처리
        if (!member.getNickname().equals(myPageDTO.getNickname())) {
            boolean flag =
                    memberRepository.existsByNickname(myPageDTO.getNickname());
            if (flag)
                throw new MemberNotFoundException("닉네임 중복으로 저장할 수 없습니다. 중복 검사 먼저 실행해주세요.");
        }

        member.change_info(myPageDTO);

        LivingPattern livingPattern = member.getLivingPattern();

        livingPatternRepository.save(livingPattern);
        memberRepository.save(member);
    }
}
