package shield.shieldbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shield.shieldbackend.dto.MemberJoinDto;
import shield.shieldbackend.dto.MemberLoginDto;
import shield.shieldbackend.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원가입
     */
    @PostMapping("/api/join")
    public ResponseEntity<MemberJoinDto> join(@RequestBody MemberJoinDto dto) throws Exception {
        MemberJoinDto res = memberService.join(dto);
        return ResponseEntity.ok(res);

    }

    /**
     * 아이디 중복확인
     * 프론트에서 해당 아이디 중복 체크 버튼을 누를 때 /api/check/id?userId=userId(검사하려는 id) 형식으로 보내면 됨.
     * 중복이 아니라면 Boolean 값인   1(true),  중복이라면   0(false)으로 결과값을 반환.
     * 결과 값으로 받은 0과1로 프론트에서 중복 검사 결과 처리.
     */
    @GetMapping("/api/check/id")
    public ResponseEntity<Boolean> checkId(@RequestParam String userId){
        Boolean res = memberService.checkId(userId);
        return ResponseEntity.ok(res);
    }

    /**
     * 로그인
     * MemberLoginDto로 받은 userId와 password를 통해 해당 아이디, 비밀번호가 있는지 확인 후
     * 없으면 null,  있으면 해당 member의 id 반환.
     */
    @PostMapping("/api/login")
    public ResponseEntity<Long> login(@RequestBody MemberLoginDto dto){
        Long res = memberService.login(dto);
        return ResponseEntity.ok(res);
    }

}
