package org.example.utility.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ApiException {
	private final String message;
	private final String field;
}
