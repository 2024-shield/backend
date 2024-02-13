package shield.shieldbackend.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shield.shieldbackend.domain.Member;
import shield.shieldbackend.dto.MemberJoinDto;
import shield.shieldbackend.dto.MemberLoginDto;
import shield.shieldbackend.repository.MemberRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public MemberJoinDto join(MemberJoinDto dto) throws Exception {
        if(!Objects.equals(dto.getPassword(), dto.getPasswordCheck())){
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

    public  Long login(MemberLoginDto dto) {
        Member member = memberRepository.findByUserId(dto.getUserId());
        if (member != null && member.getPassword().equals(dto.getPassword())) {
            return member.getId();
        }
        return null;
    }




    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
