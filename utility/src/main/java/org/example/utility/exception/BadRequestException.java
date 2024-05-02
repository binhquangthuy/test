package org.example.utility.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends ApplicationException {

    public BadRequestException (String message) {
        super(message);
    }

    public BadRequestException (String message, String field) {
        super(message, field);
    }
}
