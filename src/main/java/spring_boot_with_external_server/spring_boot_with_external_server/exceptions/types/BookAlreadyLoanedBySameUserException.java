package spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types;

public class BookAlreadyLoanedBySameUserException extends RuntimeException{
    public BookAlreadyLoanedBySameUserException(String message) {
        super(message);
    }

    public BookAlreadyLoanedBySameUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyLoanedBySameUserException(Throwable cause) {
        super(cause);
    }
}
