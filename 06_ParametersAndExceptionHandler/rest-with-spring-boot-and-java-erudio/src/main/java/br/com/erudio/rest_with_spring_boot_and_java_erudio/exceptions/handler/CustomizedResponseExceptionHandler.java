package br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.handler;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.ExceptionResponse;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest resquest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest resquest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest resquest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), resquest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
