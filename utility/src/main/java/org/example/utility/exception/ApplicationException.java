package org.example.utility.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {

    private String message;

    private String field;

    public ApplicationException(String message) {
        this.message = message;
    }

    public ApplicationException(String message, String field) {
        this.message = message;
        this.field = field;
    }
}
