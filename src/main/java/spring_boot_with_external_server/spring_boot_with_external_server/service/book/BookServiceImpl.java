package spring_boot_with_external_server.spring_boot_with_external_server.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_with_external_server.spring_boot_with_external_server.custommapper.BookMapper;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.BookDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Book;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Loans;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.BookAlreadyRegisteredException;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.BookCannotBeNullException;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.NotFoundException;
import spring_boot_with_external_server.spring_boot_with_external_server.repository.BookRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.repository.LoanRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final LoanRepository loanRepository;
    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book dbBook = bookRepository.findFirstByTitle(bookDTO.getTitle());
        if(dbBook != null){
            throw new BookAlreadyRegisteredException("this book already registered");
        }

        Book book = BookMapper.BOOK_MAPPER.toBook(bookDTO);
        Book savedBook = bookRepository.save(book);

        return BookMapper.BOOK_MAPPER.toBookDTO(savedBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List <Book> books = bookRepository.findAll();
        if(books.isEmpty()){
            throw new NotFoundException("No Books Found");
        }
        return BookMapper.BOOK_MAPPER.toBookDTOList(books);
    }

    @Override
    public BookDTO updateBook(Integer bookId, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new NotFoundException("No Book Found!!"));

        if(bookDTO == null){
            throw new BookCannotBeNullException("Book can not be null");
        }

        BookMapper.BOOK_MAPPER.updateBookFromDTO(book, bookDTO);

        Book updatedBook = bookRepository.save(book);
        return BookMapper.BOOK_MAPPER.toBookDTO(updatedBook);
    }



    @Override
    public void deleteBook(Integer bookId) {
        //manually deleting loans before deleting the book
        //to avoid foreign key integrity violation
        List<Loans> loans =  loanRepository.findByBookId(bookId);
        loanRepository.deleteAll(loans);
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookDTO findBookById(Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new NotFoundException("OOPS! No Book Found"));

        return BookMapper.BOOK_MAPPER.toBookDTO(book);
    }
}
