package spring_boot_with_external_server.spring_boot_with_external_server.dtos;

public record ApiResponse<T>(String message, T data) {

    public ApiResponse(String message){
        this(message, null);
    }
}
