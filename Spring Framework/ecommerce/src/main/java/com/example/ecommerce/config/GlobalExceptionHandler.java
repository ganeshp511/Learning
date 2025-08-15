package com.example.ecommerce.config;

import com.example.ecommerce.dto.ResponseDTO;
import org.apache.catalina.connector.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFoundException(ChangeSetPersister.NotFoundException exception){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMsg(exception.getMessage());
        responseDTO.setStatusCode("989");
        return ResponseEntity.badRequest().body(responseDTO);
    }
}