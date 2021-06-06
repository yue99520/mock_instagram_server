package com.ernie.mock_instagram_server.controller.api.response.handler;

import com.ernie.mock_instagram_server.controller.api.response.ExceptionResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseBody> exceptionHandler(Exception e) {
        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody();
        exceptionResponseBody.getErrors().add(e.getMessage());
        return new ResponseEntity<>(exceptionResponseBody, HttpStatus.BAD_REQUEST);
    }
}
