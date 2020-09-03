package com.otserv.api.presenter.config;

import com.otserv.api.core.exceptions.DomainException;
import com.otserv.api.core.exceptions.EmailOrAccountNameAlreadyInUseException;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.presenter.entities.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EmailOrAccountNameAlreadyInUseException.class})
    ResponseEntity<ApiResponse> handleEmailOrAccountNameAlreadyInUseException(EmailOrAccountNameAlreadyInUseException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DomainException.class})
    ResponseEntity<ApiResponse> handleDomainException(DomainException ex) {
        return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
