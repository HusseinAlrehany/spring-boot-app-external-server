package spring_boot_with_external_server.spring_boot_with_external_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.ApiResponse;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.LoanDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.service.loans.LoanService;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse<LoanDTO>> borrowBook(@Validated @RequestBody LoanDTO loanDTO){

        LoanDTO loanDTO1 = loanService.borrowBook(loanDTO);
        //the loanDTO1 will never be null since
        //we handle many scenarios in service layer(if not valid it throw exception)
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Book is successfully borrowed", loanDTO1));

    }



}
