package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ProyectoIntegrador.service.serviceimpl.OdontologoService;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> obtenerOdontologos(){
        logger.info("Obteniendo lista de odontologos");
        return ResponseEntity.ok(odontologoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id){
        ResponseEntity respuesta;
        OdontologoDto odontologo = odontologoService.buscarPorId(id);
        if (odontologo != null){
            logger.info("Se encontró el odontologo con id: " + id);
            respuesta = ResponseEntity.ok(odontologo);
        }else {
            logger.error("No se encontró el odontologo con id: " + id);
            respuesta = ResponseEntity.notFound().build();
        }
        return respuesta;
    }

    @PostMapping("/crear")
    public ResponseEntity<OdontologoDto> crearOdontologo(@RequestBody OdontologoDto odontologo){
        logger.info("Agregando el odontologo");
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody OdontologoDto odontologo){
        ResponseEntity<OdontologoDto> respuesta;
        if (odontologoService.actualizar(odontologo) != null){
            logger.info("Se actualizó el odontologo con id: " + odontologo.getId());
            respuesta = ResponseEntity.ok(odontologo);
        }else {
            logger.error("No se actualizó el odontologo con id: " + odontologo.getId());
            respuesta = ResponseEntity.badRequest().build();
        }
        return respuesta;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Long id){
        ResponseEntity<String> respuesta;
        if (odontologoService.borrar(id) != null){
            logger.info("Se eliminó el odontologo con id: " + id);
            respuesta = ResponseEntity.ok("Se eliminó el odontologo: " + id);
        }else {
            logger.error("No se pudo eliminar el odontologo con id: " + id);
            respuesta = ResponseEntity.badRequest().build();
        }
        return respuesta;
    }
}
