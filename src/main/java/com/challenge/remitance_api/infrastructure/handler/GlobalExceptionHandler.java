package com.challenge.remitance_api.infrastructure.handler;

import com.challenge.remitance_api.infrastructure.controller.response.ErrorResponse;
import com.challenge.remitance_api.infrastructure.exception.DailyLimitReachedException;
import com.challenge.remitance_api.infrastructure.exception.NotEnoughBalanceException;
import com.challenge.remitance_api.infrastructure.exception.UserIdNotFoundExceptionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(UserIdNotFoundExceptionException.class)
   public ResponseEntity<Object> handleResourceNotFoundException
           (UserIdNotFoundExceptionException ex, WebRequest request) {
       ErrorResponse errorResponse = new ErrorResponse(
               LocalDateTime.now(),
               ex.getMessage(),
               HttpStatus.NOT_FOUND
       );
       return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }


   @ExceptionHandler(value = {RuntimeException.class})
   public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
       ErrorResponse errorResponse = new ErrorResponse(
               LocalDateTime.now(),
               ex.getMessage(),
               HttpStatus.INTERNAL_SERVER_ERROR
       );
       return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
   }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class,
                        DailyLimitReachedException.class,
                        NotEnoughBalanceException.class})
    public ResponseEntity<Object> handleValidationExceptions(
            Exception ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                String.join("", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}