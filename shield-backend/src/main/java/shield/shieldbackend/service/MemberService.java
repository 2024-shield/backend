package shield.shieldbackend.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import shield.shieldbackend.domain.Member;
import shield.shieldbackend.dto.MemberJoinDto;
import shield.shieldbackend.dto.MemberLoginDto;
import shield.shieldbackend.dto.MyPageDto;
import shield.shieldbackend.repository.MemberRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public MemberJoinDto join(MemberJoinDto dto) throws Exception {
        if (memberRepository.existsByUserId(dto.getUserId())) {
            throw new Exception("This ID already exists");
        }
        else if(!Objects.equals(dto.getPassword(), dto.getPasswordCheck())){
            throw new Exception("Not Equal Password");
        }
        return MemberJoinDto.from(memberRepository.save(Member.from(dto)));
    }

    /**
     * 아이디 중복 검사
     */
    public Boolean checkId(String userId) {
        if(memberRepository.findByUserId(userId)==null){
            return true;
        }
        return false;
    }

    public Long login(MemberLoginDto dto) {
        Member member = memberRepository.findByUserId(dto.getUserId());
        if (member != null && member.getPassword().equals(dto.getPassword())) {
            return member.getMemberId();
        }
        return null;
    }

    // 마이페이지 유저 정보 불러오기
    public MyPageDto findUserInfo(HttpServletRequest request) {
        // 세션에서 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        System.out.println("MemberId from Session: " + session);

        Long MemberId = (Long) session.getAttribute("MemberId");

        // 아이디로 회원 정보 조회
        if (MemberId == null) {
            return null;    // 세션에 사용자 아이디가 없는 경우 null 반환
        }

        Member member = memberRepository.findByMemberId(MemberId);

        if (member == null) {
            System.out.println("User not found with user MemberId: " + MemberId);
            return null;
        }

        return new MyPageDto(member);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

}
