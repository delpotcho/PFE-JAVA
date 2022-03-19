package org.sqli.authentification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.sqli.authentification.exception.ResponseMessage;
import org.sqli.authentification.exception.UserException;

@ControllerAdvice
public class CustomResponseExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResponseMessage.class})
    public final ResponseEntity<Object> exceptionHandler(ResponseMessage ex ) {
        UserException userException = new UserException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<Object>(userException, HttpStatus.BAD_REQUEST);
    }
}
