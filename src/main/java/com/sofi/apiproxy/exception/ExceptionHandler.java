package com.sofi.apiproxy.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DownStreamException.class)
    public ResponseEntity<Object> handleDownStreamException(DownStreamException ex){
        ApiError apiError = new ApiError();
        apiError.setError(ex.getMessage());
        apiError.setCode(ex.getHttpStatus().value());
        return new ResponseEntity<>(apiError,new HttpHeaders(), ex.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex){
        //ts
        //status
        //error
        ApiError apiError = new ApiError();
        apiError.setError(ex.getMessage());
        apiError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiError,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
