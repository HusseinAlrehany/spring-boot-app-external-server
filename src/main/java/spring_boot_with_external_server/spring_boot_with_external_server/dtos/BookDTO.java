package spring_boot_with_external_server.spring_boot_with_external_server.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {

    private Integer id;

    @NotBlank(message = "Title can not be blank")
    @Size(max = 20, message = "title can not exceed 20 character length ")
    private String title;

    @NotBlank(message = "Author name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Invalid name!, only letters allowed")
    private String author;

    @NotNull(message = "Published year is required")
    @PastOrPresent(message = "Invalid Year , present or past only")
    private Date published_year;

    @NotNull(message = "availability is required")
    private Boolean available;

    @NotBlank(message = "Quantity is required")
    @Min(value = 1, message = "Minimum Quantity is 1")
    @Max(value = 50, message = "Max Quantity is 50 book")
    private Long bookQuantity;

}
