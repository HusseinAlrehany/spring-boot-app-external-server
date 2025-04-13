package spring_boot_with_external_server.spring_boot_with_external_server.service.loans;

import spring_boot_with_external_server.spring_boot_with_external_server.dtos.LoanDTO;

import java.util.Optional;

public interface LoanService {
    LoanDTO borrowBook(LoanDTO loanDTO);
}
