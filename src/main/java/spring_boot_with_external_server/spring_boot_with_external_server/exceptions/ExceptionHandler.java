package spring_boot_with_external_server.spring_boot_with_external_server.exceptions;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    //helper method to decrease code repetition
    private ResponseEntity<Object> errorResponseBuilder(String message, HttpStatus status){
             return new ResponseEntity<>(new ErrorResponse(
                     message,
                     status,
                     LocalDateTime.now()),
                     status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex){
        return errorResponseBuilder(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> invalidInputExceptionHandler(InvalidInputException ex){
        return errorResponseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException ex){
        return errorResponseBuilder(ex.getMessage(), HttpStatus.NOT_FOUND);

    }
    @org.springframework.web.bind.annotation.ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<Object> bookNotAvailableException(BookNotAvailableException ex){

        return errorResponseBuilder(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(BookAlreadyRegisteredException.class)
    public ResponseEntity<Object> bookAlreadyRegisteredExceptionHandler(BookAlreadyRegisteredException ex){
        return errorResponseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(MaxBooksAmountExceededException.class)
    public ResponseEntity<Object> maxBooksAmountExceededExceptionHandler(MaxBooksAmountExceededException ex){

        return errorResponseBuilder(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BookCannotBeNullException.class)
    public ResponseEntity<Object> bookCannotBeNullExceptionHandler(BookCannotBeNullException ex){
        return errorResponseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(MemberAlreadyRegisteredException.class)
    public ResponseEntity<Object> memberAlreadyRegisteredExceptionHandler(MemberAlreadyRegisteredException ex){
        return errorResponseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BookAlreadyLoanedBySameUserException.class)
    public ResponseEntity<Object> memberAlreadyRegisteredExceptionHandler(BookAlreadyLoanedBySameUserException ex){
        return errorResponseBuilder(ex.getMessage(), HttpStatus.CONFLICT);
    }


    //to validate that only true or false will be input
    //HttpMessageNotReadableException is thrown when a non-boolean value like(hello)
    //then spring fails to deserialize the JSON into java object
    //then check if the root cause is MisMatchedInputException , which happens when
    //non-boolean value (like "hello") is provided
    //if it's it will return clear user-friendly error message
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> misMatchedInputExceptionHandler(HttpMessageNotReadableException ex){

          if(ex.getCause() instanceof MismatchedInputException){
             return errorResponseBuilder("Invalid input format, Enter valid format",
                                         HttpStatus.BAD_REQUEST);
          }
              return errorResponseBuilder("Invalid Request Body Format",
                                        HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }



}
