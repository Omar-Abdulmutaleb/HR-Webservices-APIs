package gov.iti.jets.utils.exceptionBuilder;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final String description;
    private final int code;
    public BusinessException(String message, int code, String description) {
        super(message);
        this.description = description;
        this.code = code;
    }
}
