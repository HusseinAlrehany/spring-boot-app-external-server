package spring_boot_with_external_server.spring_boot_with_external_server.service.book;

import spring_boot_with_external_server.spring_boot_with_external_server.dtos.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO saveBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO updateBook(Integer bookId, BookDTO bookDTO);

    void deleteBook(Integer bookId);

    BookDTO findBookById(Integer bookId);
}
