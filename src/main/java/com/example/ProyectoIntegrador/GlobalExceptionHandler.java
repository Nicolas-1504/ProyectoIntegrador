package com.example.ProyectoIntegrador;

import com.example.ProyectoIntegrador.controller.OdontologoController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> todosLosErrores(Exception ex){
        logger.error(ex.getMessage());
        return new ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
