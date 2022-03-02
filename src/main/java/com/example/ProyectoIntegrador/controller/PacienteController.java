package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.service.serviceimpl.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;
    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> obtenerPacientes(){
        logger.info("Obteniendo lista de pacientes");
        return ResponseEntity.ok(pacienteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id){
        ResponseEntity respuesta;
        PacienteDto pacienteDto = pacienteService.buscarPorId(id);
        if (pacienteDto != null){
            logger.info("Se encontró el paciente con id: " + id);
            respuesta = ResponseEntity.ok(pacienteDto);
        }else {
            logger.error("No se encontró el paciente con id: " + id);
            respuesta = ResponseEntity.notFound().build();
        }
        return respuesta;
    }

    @PostMapping("/crear")
    public ResponseEntity<PacienteDto> crearPaciente(@RequestBody PacienteDto paciente){
        logger.info("Agregando el paciente");
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody PacienteDto paciente){
        ResponseEntity<PacienteDto> respuesta;
        if (pacienteService.actualizar(paciente) != null){
            logger.info("Se actualizó el paciente con id: " + paciente.getId());
            respuesta = ResponseEntity.ok(paciente);
        }else {
            logger.error("No se actualizó el paciente con id: " + paciente.getId());
            respuesta = ResponseEntity.badRequest().build();
        }
        return respuesta;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Long id){
        ResponseEntity<String> respuesta;
        if (pacienteService.borrar(id) != null){
            logger.info("Se eliminó el paciente con id: " + id);
            respuesta = ResponseEntity.ok("Se eliminó el paciente: " + id);
        }else {
            logger.error("No se pudo eliminar el paciente con id: " + id);
            respuesta = ResponseEntity.badRequest().build();
        }
        return respuesta;
    }
}
