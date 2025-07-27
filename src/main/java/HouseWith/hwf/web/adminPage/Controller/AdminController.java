package HouseWith.hwf.web.adminPage.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * 관리자 페이지의 필요성
 * 관리자 페이지에서 학사 일정에 따른 관리 용이성 + 학기 별 글 생성 관리 / 이격 용이 + 사용량 파악 용이
 *
 * 관리자 엔티티 + 레포 생성으로 관리자 계정 생성 필요
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin/info")
public class AdminController {
    @PostMapping("set_update/date")
    public String setUpdateDate(@RequestParam LocalDateTime date) {
        date = LocalDateTime.now();
        log.info("api 수정이 " + date + " 에 완료되었습니다.");
        return "api 수정이 " + date + " 에 완료되었습니다.";
    }
}
