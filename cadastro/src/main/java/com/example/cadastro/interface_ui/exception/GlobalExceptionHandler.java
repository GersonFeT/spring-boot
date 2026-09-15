package com.example.cadastro.interface_ui.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

import static com.example.cadastro.interface_ui.exception.ProblemDetailUtils.buildProblem;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.BAD_REQUEST,
                "Parameter type invalid",
                String.format(
                        "Parameter '%s' must be '%s'. Received value: '%s'",
                        ex.getName(),
                        ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown",
                        ex.getValue()
                ),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problem = buildProblem(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                "One or more invalid field",
                request.getRequestURI()
        );

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        return buildProblem(
                HttpStatus.CONFLICT,
                "Data conflict",
                "The request violates data integration restriction",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(InternalError.class)
    public ProblemDetail handleInternalError(InternalError ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                "Internal server error ocurred.",
                request.getRequestURI()
        );
    }
}