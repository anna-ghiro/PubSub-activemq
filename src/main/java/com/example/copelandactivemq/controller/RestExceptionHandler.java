package com.example.copelandactivemq.controller;

import com.example.copelandactivemq.exception.UnauthorizedException;
import com.example.copelandactivemq.model.ErrorMessageJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessageJson> handleUnauthorizedException(UnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorMessageJson.builder().message(exception.getMessage()).build());
    }
}
