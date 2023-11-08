package com.juliar.tehnoskytask.controller.advice;

import com.juliar.tehnoskytask.errors.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.InstanceNotFoundException;
import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackages = "com.juliar.tehnoskytask.controller")
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handle(IllegalArgumentException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(UsernameNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }

        @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(AuthenticationException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(NoSuchElementException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(InstanceNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handle(EntityNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }


    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handle(UnsupportedOperationException e) {
        return new ErrorMessage(e.getMessage());
    }
}
