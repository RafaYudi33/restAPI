package br.com.rafay.restAPI.Handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.rafay.restAPI.Exceptions.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
        Exception e, WebRequest request){
        
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            new Date(), e.getMessage(), request.getDescription(false));
        
            return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
