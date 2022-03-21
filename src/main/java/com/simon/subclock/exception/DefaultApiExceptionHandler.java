package com.simon.subclock.exception;

import com.simon.subclock.model.Constants;
import com.simon.subclock.model.Fault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ValidationException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.stream.Collectors;


@ControllerAdvice
public class DefaultApiExceptionHandler {

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Fault> handleResponseStatusException(ResponseStatusException ex) {
        Fault fault = new Fault()
                .message(ex.getReason())
                .dateTime(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMATTER_PATTERN)))
                .status(ex.getStatus().name());
        return ResponseEntity.status(ex.getStatus()).body(fault);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Fault> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Fault fault = new Fault()
                .message(extractMessageMethodArgumentNotValid(ex))
                .dateTime(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMATTER_PATTERN)))
                .status(HttpStatus.BAD_REQUEST.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fault);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Fault> handleValidationException(ValidationException ex) {
        Fault fault = new Fault()
                .message(ex.getMessage())
                .dateTime(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMATTER_PATTERN)))
                .status(HttpStatus.BAD_REQUEST.name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fault);
    }

    private String extractMessageMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return String.format("Constraint validation: %s.", ex.getBindingResult().getFieldErrors().stream()
                .map(getFieldErrorStringFunction())
                .sorted()
                .collect(Collectors.joining("; ")));
    }

    private Function<FieldError, String> getFieldErrorStringFunction() {
        return fe -> String.format("%s %s", fe.getField(), fe.getDefaultMessage());
    }
}
