package es.daw.hotel_api.exception;

import es.daw.hotel_api.dto.error.ErrorResponseDto;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> hotelNotFoundException(HotelNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(LocalDateTime.now(), ex.getMessage()));
    }

    @ExceptionHandler(HabitacionNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> habitacionNotFoundException(HabitacionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(LocalDateTime.now(), ex.getMessage()));
    }

    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> categoriaNotFoundException(CategoriaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(LocalDateTime.now(), ex.getMessage()));
    }

    @ExceptionHandler(HabitacionOcupadaException.class)
    public ResponseEntity<ErrorResponseDto> habitacionOcupadaException(HabitacionOcupadaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDto(LocalDateTime.now(), ex.getMessage()));
    }
}

