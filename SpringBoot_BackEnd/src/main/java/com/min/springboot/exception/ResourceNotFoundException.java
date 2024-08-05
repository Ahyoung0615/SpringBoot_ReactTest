package com.min.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO 003
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8005357596276819294L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
