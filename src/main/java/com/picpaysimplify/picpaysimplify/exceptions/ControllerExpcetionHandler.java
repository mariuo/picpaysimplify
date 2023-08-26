package com.picpaysimplify.picpaysimplify.exceptions;

import com.picpaysimplify.picpaysimplify.dtos.ExceptionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExpcetionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO dto = new ExceptionDTO("User already exists.", "400");
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception){
        ExceptionDTO dto = new ExceptionDTO("User not found", "404");
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.badRequest().body(dto);
    }

}
