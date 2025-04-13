package spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types;

public class BookCannotBeNullException extends RuntimeException{

    public BookCannotBeNullException(String message) {
        super(message);
    }

    public BookCannotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookCannotBeNullException(Throwable cause) {
        super(cause);
    }
}
