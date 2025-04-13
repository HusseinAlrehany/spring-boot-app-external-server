package spring_boot_with_external_server.spring_boot_with_external_server.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
}
