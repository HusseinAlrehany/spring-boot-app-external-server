package spring_boot_with_external_server.spring_boot_with_external_server.custommapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.BookDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Book;

import java.util.List;

@Mapper
public interface BookMapper {
     BookMapper  BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

     Book toBook(BookDTO bookDTO);

     BookDTO toBookDTO(Book book);

     List<Book> toBookList(List<BookDTO> bookDTOS);

    List<BookDTO> toBookDTOList(List<Book> books);

    //for updating book from bookDTO
    //instead of manually set every single field
    //this @Mapping ignores mapping the id
    @Mapping(target = "id", ignore = true)
    void updateBookFromDTO(@MappingTarget Book book, BookDTO bookDTO);
}
