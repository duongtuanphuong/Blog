package com.example.blog.payload.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BadRequestResponse extends RuntimeException {
	private static final long serialVersionUID = -6593330219878485669L;

    private final HttpStatus httpStatus;

    private final String message;

}
