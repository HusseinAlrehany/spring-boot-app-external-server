package spring_boot_with_external_server.spring_boot_with_external_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Loans;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loans, Integer> {

    //comment this repo method (findAllByMemberId)
    //why? this method loads full objects in the memory which not needed in such scenario(bad for performance)
    //because it uses (select *) behind the scene
    //so instead we need only to count number of loans per member without loading the full objects
    //by using (countByMemberId) it uses (COUNT(*)) to only count number of loans per member
    //List <Loans> findAllByMemberId(Integer memberId);


    Integer countByMemberId(Integer memberId);

    List<Loans> findByBookId(Integer bookId);

    List<Loans> findByBookIdAndMemberId(Integer bookId, Integer memberId);
}
