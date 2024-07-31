package com.lcaohoanq.Spring_Snake_Game.exception;

import com.lcaohoanq.Spring_Snake_Game.dto.ApiResponse;
import com.lcaohoanq.Spring_Snake_Game.dto.MailResponse;
import com.lcaohoanq.Spring_Snake_Game.dto.UserResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse> userNotFoundHandler(UserNotFoundException ex) {
        if(ex.getMessage().contains("email")) {
            return new ResponseEntity<>(new MailResponse(ex.getMessage(), "error"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserResponse(ex.getMessage(), "error"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserHasBeenBannedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> userNotFoundHandler(UserHasBeenBannedException ex) {
        return new ResponseEntity<>(new UserResponse(ex.getMessage(), "error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}