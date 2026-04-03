package commercadona.supermarket.infraestructure.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import commercadona.supermarket.domain.exception.NotFoundException;
import commercadona.supermarket.domain.exception.RequestException;
import commercadona.supermarket.infraestructure.dtos.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(500)
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(404)
                        .build());
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RequestException ex) {
        return ResponseEntity
                .status(HttpStatus.REQUEST_TIMEOUT)
                .body(ErrorResponse.builder()
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(500)
                        .build());
    }
}