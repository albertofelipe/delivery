package com.projectalberto.delivery.api.exceptionHandler;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldsBody> fields = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new FieldsBody(((FieldError) error).getField(),
                        messageSource.getMessage(error, Locale.US)))
                .collect(Collectors.toList());

        var standardError = new StandardError(
                status.value(),
                OffsetDateTime.now(),
                "One or more fields are invalid!",
                fields
        );

        return handleExceptionInternal(ex, standardError, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex,
                                                        WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        var standardError = new StandardError(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );

        return handleExceptionInternal(ex, standardError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex,
                                                        WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        var standardError = new StandardError(
                status.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                null
        );

        return handleExceptionInternal(ex, standardError, new HttpHeaders(), status, request);
    }
}
