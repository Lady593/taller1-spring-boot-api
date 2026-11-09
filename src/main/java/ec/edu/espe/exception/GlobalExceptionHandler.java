package ec.edu.espe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja la excepcion cuando un producto NO es encontrado (HTTP 404)
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity
    manejarProductoNoEncontrado(ProductoNoEncontradoException ex) {
        Map<String, Object> respuestaError = new HashMap<>();
        respuestaError.put("timestamp", LocalDateTime.now());
        respuestaError.put("codigoEstado", HttpStatus.NOT_FOUND.value());
        respuestaError.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        respuestaError.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaError);
    }

    // Maneja la excepcion cuando un producto es INVALIDO (precio <= 0 o stock < 0) (HTTP 400)
    @ExceptionHandler(ProductoInvalidoException.class)
    public ResponseEntity
    manejarProductoInvalido(ProductoInvalidoException ex) {
        Map<String, Object> respuestaError = new HashMap<>();
        respuestaError.put("timestamp", LocalDateTime.now());
        respuestaError.put("codigoEstado", HttpStatus.BAD_REQUEST.value());
        respuestaError.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        respuestaError.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
    }
}