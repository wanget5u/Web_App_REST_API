package pl.edu.pjwstk.tpo6_gm_s31230.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.pjwstk.tpo6_gm_s31230.exceptions.ItemNotFoundException;
import pl.edu.pjwstk.tpo6_gm_s31230.exceptions.ReadImageException;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception exception)
    {return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occured: " + exception.getMessage());}

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFound(ItemNotFoundException itemNotFoundException)
    {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(itemNotFoundException.getMessage());}

    @ExceptionHandler(ReadImageException.class)
    public ResponseEntity<String> handleItemNotFound(ReadImageException readImageException)
    {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(readImageException.getMessage());}
}
