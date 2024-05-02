package org.example.utility.exception.handler;

import org.example.utility.exception.BadRequestException;
import org.example.utility.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.BindException;

@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(value = {DataNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException e) {
		return new ResponseEntity<>(new ApiException(e.getMessage(), e.getField()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {BadRequestException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
		return new ResponseEntity<>(new ApiException(e.getMessage(), e.getField()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ResponseEntity<>(new ApiException(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null), HttpStatus.BAD_REQUEST);
	}
}
