package com.sofi.apiproxy.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class DownStreamException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;

    public DownStreamException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
