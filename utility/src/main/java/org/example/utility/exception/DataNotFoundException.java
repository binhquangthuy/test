package org.example.utility.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataNotFoundException extends ApplicationException {

    public DataNotFoundException (String message) {
        super(message);
    }

    public DataNotFoundException (String message, String field) {
        super(message, field);
    }
}
