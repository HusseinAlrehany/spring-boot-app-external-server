package spring_boot_with_external_server.spring_boot_with_external_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findFirstByTitle(String title);
}
