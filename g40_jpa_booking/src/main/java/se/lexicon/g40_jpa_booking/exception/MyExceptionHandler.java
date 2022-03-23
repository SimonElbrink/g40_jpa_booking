package se.lexicon.g40_jpa_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import se.lexicon.g40_jpa_booking.model.dto.view.ExceptionResponseDTO;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler {

    private ExceptionResponseDTO build(String message, WebRequest request, HttpStatus httpStatus){
        ExceptionResponseDTO response = new ExceptionResponseDTO();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(httpStatus.value());
        response.setError(httpStatus.name());
        response.setMessage(message);
        response.setPath(request.getDescription(false));

       return response;
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNullPointerException(NullPointerException ex, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(build(ex.getMessage(),request,status));

    }

    @ExceptionHandler(AppResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAppResourceNotFoundException(AppResourceNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(status)
                .body(build(ex.getMessage(), request, status));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(build(ex.getMessage(), request, HttpStatus.BAD_REQUEST));
    }

}
