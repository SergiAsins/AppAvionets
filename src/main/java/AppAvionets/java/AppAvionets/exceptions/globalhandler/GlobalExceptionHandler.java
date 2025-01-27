package AppAvionets.java.AppAvionets.exceptions.globalhandler;

import AppAvionets.java.AppAvionets.exceptions.AirCompanyInvalidFormatException;
import AppAvionets.java.AppAvionets.exceptions.flights.AirCompanyErrorFlightException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manage general validations (ex: @Valid failed)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Manage InvalidFlightNumber
    @ExceptionHandler(AirCompanyInvalidFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidFlightNumber(AirCompanyInvalidFormatException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Manage ErrorFlights
    @ExceptionHandler(AirCompanyErrorFlightException.class)
    public ResponseEntity<Map<String, String>> handleErrorFlightCreation(AirCompanyErrorFlightException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




    // Manage any other exception
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "An unexpected error occurred");
        response.put("details", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}