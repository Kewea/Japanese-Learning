package com.japanese.japstudytool.exceptions.handlers;

import com.japanese.japstudytool.exceptions.GrammarException;
import com.japanese.japstudytool.exceptions.responses.GrammarExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GrammarExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleGrammarException(GrammarException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new GrammarExceptionResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
