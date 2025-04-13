package spring_boot_with_external_server.spring_boot_with_external_server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;

    private Date published_year;
    private Boolean available;

    private Long bookQuantity;
}
