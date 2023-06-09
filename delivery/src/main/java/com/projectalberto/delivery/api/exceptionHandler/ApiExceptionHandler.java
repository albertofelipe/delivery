package com.projectalberto.delivery.api.exceptionHandler;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex,
                                                        WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        var standardError = new StandardError(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage()
        );

        return handleExceptionInternal(ex, standardError,
                new HttpHeaders(), status, request);
    }
}
