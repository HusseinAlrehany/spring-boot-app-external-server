package spring_boot_with_external_server.spring_boot_with_external_server.custommapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.LoanDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Loans;

import java.util.List;

@Mapper
public interface LoanMapper {

    LoanMapper LOAN_MAPPER = Mappers.getMapper(LoanMapper.class);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "memberId", target = "member.id")
    Loans toLoans(LoanDTO loanDTO);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "member.id", target = "memberId")
    LoanDTO toLoanDTO(Loans loans);

}
