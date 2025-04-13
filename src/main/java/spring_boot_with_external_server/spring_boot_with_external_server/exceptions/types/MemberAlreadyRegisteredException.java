package spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types;

public class MemberAlreadyRegisteredException extends RuntimeException{
    public MemberAlreadyRegisteredException(String message) {
        super(message);
    }

    public MemberAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
}
