package gov.iti.jets.utils;

import gov.iti.jets.utils.exceptionBuilder.BusinessException;

public interface Validator {
    default void validateId(Integer id) {
        if (id == 0 || Integer.toString(id).startsWith("0")) {
            throw new BusinessException("Invalid ID", 400, "ID must be a positive number and cannot start with 0");
        }
    }
}