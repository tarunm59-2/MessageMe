package com.example.chatapp_back.common_stuff.error_handling_stuff.tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;

import java.util.stream.Collectors;

@ControllerAdvice
@Order(1)  // High priority for catching all exceptions
public class GlobalExceptionHandler {

    private static final String ERROR_FIELD = "errors";
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle validation errors for MethodArgumentNotValidException (from annotations like @NotNull)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException exception) {
        return createProblemDetail(exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage())));
    }

    // Handle Bean Validation exceptions (like @NotNull, @Size)
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException exception) {
        return createProblemDetail(exception.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(this::extractFieldName, ConstraintViolation::getMessage)));
    }

    // Handle any generic runtime exceptions
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception exception) {
        logger.error("An unexpected error occurred: ", exception);
        return createProblemDetail(Map.of("general", "An unexpected error occurred. Please try again later."));
    }

    // Handle method argument mismatch errors
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        logger.error("Argument mismatch error: ", exception);
        return createProblemDetail(Map.of("argument", "Invalid argument type provided."));
    }

    // Utility method to create the ProblemDetail response
    private ProblemDetail createProblemDetail(Map<String, String> errors) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "One or more issues occurred. Check the 'errors' for details.");
        problem.setTitle("Validation or System Error");
        problem.setProperty(ERROR_FIELD, errors);
        return problem;
    }

    // Extract field name from constraint violation path
    private String extractFieldName(ConstraintViolation<?> violation) {
        String propertyPath = violation.getPropertyPath().toString();
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
    }
}


