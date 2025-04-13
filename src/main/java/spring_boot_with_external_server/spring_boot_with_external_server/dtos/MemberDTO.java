package spring_boot_with_external_server.spring_boot_with_external_server.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
@Data
public class MemberDTO {

    private Integer id;

    @NotBlank(message = "member name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid member name")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    private String email;

    private Date join_date;

}
