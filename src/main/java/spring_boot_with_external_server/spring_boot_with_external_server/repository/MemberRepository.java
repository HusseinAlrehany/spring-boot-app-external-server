package spring_boot_with_external_server.spring_boot_with_external_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findFirstByEmail(String email);
}
