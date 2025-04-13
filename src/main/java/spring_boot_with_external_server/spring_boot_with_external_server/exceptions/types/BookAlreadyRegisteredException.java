package spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types;

public class BookAlreadyRegisteredException extends RuntimeException{
    public BookAlreadyRegisteredException(String message) {
        super(message);
    }

    public BookAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
}
