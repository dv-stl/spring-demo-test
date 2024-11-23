package com.example.slightlycomplex.web;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Wraps validation errors in
     * {
     * "errors": [
     * {
     * "field": "brand",
     * "message": "must not be null"
     * },
     * {
     * "field": "engineCapacity",
     * "message": "must be greater than 0"
     * }
     * ]
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<ValidationError> errorMessages = errors.stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .sorted(Comparator.comparing(ValidationError::getField))
                .collect(Collectors.toList());

        errorMessages.sort(Comparator.comparing(ValidationError::getField));

        return ResponseEntity
                .badRequest()
                .body(new ValidationErrors(errorMessages));
    }

    // Custom response structure for errors
    public static class ValidationError {
        private String field;
        private String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        // Getters and Setters
        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Wrapper class for multiple validation errors
    public static class ValidationErrors {
        private List<ValidationError> errors;

        public ValidationErrors(List<ValidationError> errors) {
            this.errors = errors;
        }

        // Getter
        public List<ValidationError> getErrors() {
            return errors;
        }

        public void setErrors(List<ValidationError> errors) {
            this.errors = errors;
        }
    }
}
