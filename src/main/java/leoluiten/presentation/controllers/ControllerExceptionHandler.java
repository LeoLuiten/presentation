package leoluiten.presentation.controllers;

import jakarta.persistence.EntityNotFoundException;
import leoluiten.presentation.dtos.common.ErrorApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

/**
 * Global exception handler for controllers, using Spring's @ControllerAdvice.
 * It handles various exceptions and returns appropriate error responses.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handles generic exceptions that are not specifically handled elsewhere.
     *
     * @param exception the exception that was thrown.
     * @return a ResponseEntity containing an ErrorApi object with the error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleError(Exception exception) {
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles validation errors specifically related to method arguments (e.g., validation annotations).
     *
     * @param exception the MethodArgumentNotValidException that was thrown.
     * @return a ResponseEntity containing an ErrorApi object with the error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleError(MethodArgumentNotValidException exception) {
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles validation errors specifically related to Response status exceptions
     *
     * @param exception the ResponseStatusException that was thrown.
     * @return a ResponseEntity containing an ErrorApi object with the error details.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorApi> handleError(ResponseStatusException exception) {
        ErrorApi error = buildError(exception.getReason(), HttpStatus.valueOf(exception.getStatusCode().value()));
        return ResponseEntity.status(exception.getStatusCode()).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorApi> handleError(EntityNotFoundException exception) {
        ErrorApi error = buildError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Builds an ErrorApi object based on the provided message and HTTP status.
     *
     * @param message the error message to be included in the ErrorApi object.
     * @param status the HTTP status code to be associated with the error.
     * @return an ErrorApi object containing the error details.
     */
    private ErrorApi buildError(String message, HttpStatus status) {
        return ErrorApi.builder()
                .timestamp(String.valueOf(Timestamp.from(ZonedDateTime.now().toInstant())))
                .error(status.getReasonPhrase())
                .status(status.value())
                .message(message)
                .build();
    }
}
