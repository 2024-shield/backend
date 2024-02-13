package shield.shieldbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shield.shieldbackend.domain.Member;


public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUserId(String userId);

    Member findByPassword(String password);
}
