package gov.iti.jets.utils.exceptionBuilder;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ExceptionMessage implements Serializable {
    private String errorMessage;
    private int errorCode;
    private String errorDescription;
}
