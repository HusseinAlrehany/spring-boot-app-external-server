package spring_boot_with_external_server.spring_boot_with_external_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.ApiResponse;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.BookDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.service.book.BookService;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<ApiResponse<BookDTO>> createBook(@Validated  @RequestBody BookDTO bookDTO){
        BookDTO bookDTO1 = bookService.saveBook(bookDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("Book Created Success!", bookDTO1));
    }

    @GetMapping("/books")
    public ResponseEntity<ApiResponse<List<BookDTO>>> getAllBooks(){
        List<BookDTO> bookDTOS = bookService.getAllBooks();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("All Books Retrieved", bookDTOS));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<ApiResponse<BookDTO>> updateBook(@PathVariable Integer bookId,
                                                          @Validated @RequestBody BookDTO bookDTO){
        BookDTO bookDTO1 = bookService.updateBook(bookId, bookDTO);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("Book updated Success!", bookDTO1));
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<ApiResponse<BookDTO>> deleteBook(@PathVariable Integer bookId){
            bookService.deleteBook(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("Book Deleted Success!"));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<ApiResponse<BookDTO>> findBookById(@PathVariable Integer bookId){
          BookDTO bookDTO = bookService.findBookById(bookId);
          return ResponseEntity.status(HttpStatus.OK)
                  .body(new ApiResponse<>("Success!", bookDTO));
    }

}
