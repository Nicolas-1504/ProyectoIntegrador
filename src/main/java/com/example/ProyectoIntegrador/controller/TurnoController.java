package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.service.serviceimpl.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    TurnoService turnoService;
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDto>> obtenerTurnos(){
        logger.info("Obteniendo lista de turnos");
        return ResponseEntity.ok(turnoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id){
        ResponseEntity respuesta;
        TurnoDto turnoDto = turnoService.buscarPorId(id);
        if (turnoDto != null){
            logger.info("Se encontró el turno con id: " + id);
            respuesta = ResponseEntity.ok(turnoDto);
        }else {
            logger.error("No se encontró el turno con id: " + id);
            respuesta = ResponseEntity.notFound().build();
        }
        return respuesta;
    }

    @PostMapping("/crear")
    public ResponseEntity<TurnoDto> crearTurno(@RequestBody TurnoDto turnoDto){
        logger.info("Agregando el turno");
        return ResponseEntity.ok(turnoService.guardar(turnoDto));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@RequestBody TurnoDto turnoDto){
        ResponseEntity<TurnoDto> respuesta;
        if (turnoService.actualizar(turnoDto) != null){
            logger.info("Se actualizó el turno con id: " + turnoDto.getId());
            respuesta = ResponseEntity.ok(turnoDto);
        }else {
            logger.error("No se actualizó el turno con id: " + turnoDto.getId());
            respuesta = ResponseEntity.badRequest().build();
        }
        return respuesta;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Long id){
        ResponseEntity<String> respuesta;
        if (turnoService.borrar(id) != null){
            logger.info("Se eliminó el turno con id: " + id);
            respuesta = ResponseEntity.ok("Se eliminó el turno con id: " + id);
        }else {
            logger.error("No se pudo eliminar el turno con id: " + id);
            respuesta = ResponseEntity.badRequest().build();
        }
        return respuesta;
    }

    @GetMapping("/dias/{dias}")
    public ResponseEntity<List<TurnoDto>> obtenerTurnosPorDias(@PathVariable Long dias){
        return ResponseEntity.ok(turnoService.obtenerTurnosPorDias(dias));
    }
}
