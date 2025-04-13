package spring_boot_with_external_server.spring_boot_with_external_server.service.loans;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_with_external_server.spring_boot_with_external_server.custommapper.LoanMapper;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.LoanDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Book;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Loans;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Member;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.BookAlreadyLoanedBySameUserException;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.BookNotAvailableException;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.MaxBooksAmountExceededException;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.NotFoundException;
import spring_boot_with_external_server.spring_boot_with_external_server.repository.BookRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.repository.LoanRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.repository.MemberRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.service.loans.LoanService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;
    @Override
    public LoanDTO borrowBook(LoanDTO loanDTO) {

        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(()-> new NotFoundException("No Book Found"));

        Member member = memberRepository.findById(loanDTO.getMemberId())
                .orElseThrow(()-> new NotFoundException("No Member Found!"));

      validateLoanConditions(book, member);

      long updatedQuantity = book.getBookQuantity() - 1;

      Loans loans = LoanMapper.LOAN_MAPPER.toLoans(loanDTO);
      loans.setBook(book);
      loans.setMember(member);

      Loans savedLoans = loanRepository.save(loans);

      if(updatedQuantity == 0){
          book.setBookQuantity((long)0);
          book.setAvailable(false);
      } else {
          book.setBookQuantity(updatedQuantity);
          book.setAvailable(true);
      }

      bookRepository.save(book);

        return LoanMapper.LOAN_MAPPER.toLoanDTO(savedLoans);
    }

    public void validateLoanConditions(Book book, Member member){

        List<Loans> loansList = loanRepository.findByBookIdAndMemberId(book.getId(), member.getId());

        if(isAlreadyLoanedByUser(loansList)){
            throw new BookAlreadyLoanedBySameUserException("This Book is Already loaned by you!");
        }

        if(!book.getAvailable()){
            throw new BookNotAvailableException("This book not available for loan");
        }
        if(isMaxLoanExceed(member.getId())){
            throw new MaxBooksAmountExceededException("Max book allowed for loan per member is 3 at once");
        }

    }
    public boolean isMaxLoanExceed(Integer memberId){

        return loanRepository.countByMemberId(memberId) >= 3;

    }
    public boolean isAlreadyLoanedByUser(List<Loans> loans){
        return !loans.isEmpty();
    }

}
