package spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types;

public class MaxBooksAmountExceededException extends RuntimeException{
    public MaxBooksAmountExceededException(String message) {
        super(message);
    }

    public MaxBooksAmountExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaxBooksAmountExceededException(Throwable cause) {
        super(cause);
    }
}
