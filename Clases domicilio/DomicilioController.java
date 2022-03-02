package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.DomicilioDto;
import com.example.ProyectoIntegrador.service.serviceimpl.DomicilioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;
    private static final Logger logger = Logger.getLogger(DomicilioController.class);

    @GetMapping("/todos")
    public ResponseEntity<List<DomicilioDto>> obtenerDomicilios(){
        logger.info("Obteniendo lista de Domicilios");
        return ResponseEntity.ok(domicilioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomicilioDto> buscarDomicilioPorId(@PathVariable Long id){
        ResponseEntity respuesta = ResponseEntity.notFound().build();
        DomicilioDto domicilio = domicilioService.buscarPorId(id);
        if (domicilio != null){
            respuesta = ResponseEntity.ok(domicilio);
        }
        return respuesta;
    }

    @PostMapping("/crear")
    public ResponseEntity<DomicilioDto> crearDomicilio(@RequestBody DomicilioDto domicilio){
        return ResponseEntity.ok(domicilioService.guardar(domicilio));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DomicilioDto> actualizarDomicilio(@RequestBody DomicilioDto domicilio){
        ResponseEntity<DomicilioDto> respuesta = ResponseEntity.badRequest().build();
        if (domicilioService.actualizar(domicilio) != null){
            respuesta = ResponseEntity.ok(domicilio);
        }
        return respuesta;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarDomicilio(@PathVariable Long id){
        ResponseEntity<String> respuesta = ResponseEntity.badRequest().build();
        if (domicilioService.borrar(id) != null){
            respuesta = ResponseEntity.ok("Se eliminó el domicilio");
        }
        return respuesta;
    }
}
