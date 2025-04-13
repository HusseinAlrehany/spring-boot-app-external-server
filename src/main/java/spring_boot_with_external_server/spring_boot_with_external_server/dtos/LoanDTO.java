package spring_boot_with_external_server.spring_boot_with_external_server.dtos;
import lombok.Data;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Book;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Member;

import java.util.Date;

@Data
public class LoanDTO {

    private Integer id;
    private Date loan_date;
    private Date return_date;

    private Integer bookId;

    private Integer memberId;
}
