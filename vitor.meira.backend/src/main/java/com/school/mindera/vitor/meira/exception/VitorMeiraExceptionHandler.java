package com.school.mindera.vitor.meira.exception;

import com.school.mindera.vitor.meira.error.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class VitorMeiraExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            SamuraiNotFoundException.class})
    public ResponseEntity<Error> handlerNotFoundException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            SamuraiAlreadyExistsException.class})
    public ResponseEntity<Error> handlerConflictException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Error> handlerAnyOtherException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = Error.builder()
                .timestamp(new Date())
                .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .method(((ServletWebRequest) request).getRequest().getMethod())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Error> buildErrorResponse(Exception ex, HttpServletRequest request, HttpStatus httpStatus) {
        Error error = Error.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error, httpStatus);
    }
}
