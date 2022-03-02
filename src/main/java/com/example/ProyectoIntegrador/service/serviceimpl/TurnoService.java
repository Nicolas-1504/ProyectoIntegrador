package com.example.ProyectoIntegrador.service.serviceimpl;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.persistance.entities.Turno;
import com.example.ProyectoIntegrador.persistance.repository.TurnoRepository;
import com.example.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService implements IService<TurnoDto> {

    TurnoRepository turnoRepository;
    PacienteService pacienteService;
    OdontologoService odontologoService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoDto guardar(TurnoDto entidad) {
        if (entidad != null && validarOdontologoYPaciente(entidad.getPaciente().getId(),entidad.getOdontologo().getId())){
            Turno turno = turnoRepository.save(entidad.toEntity());
            entidad.setId(turno.getId());
            entidad.setOdontologo(odontologoService.buscarPorId(entidad.getOdontologo().getId()).toEntity());
            entidad.setPaciente(pacienteService.buscarPorId(entidad.getPaciente().getId()).toEntity());
        }
        return entidad;
    }

    @Override
    public List<TurnoDto> obtenerTodos() {
        List<TurnoDto> turnos = new ArrayList<>();
        for (Turno turno:turnoRepository.findAll()) {
            turnos.add(new TurnoDto(turno));
        }
        return turnos;
    }

    @Override
    public TurnoDto buscarPorId(Long id) {
        TurnoDto turnoDto = null;
        Turno turno = turnoRepository.findById(id).orElse(null);
        if (turno != null){
            turnoDto = new TurnoDto(turno);
        }
        return turnoDto;
    }

    @Override
    public TurnoDto actualizar(TurnoDto entidad) {
        TurnoDto turnoDto = null;
        if (buscarPorId(entidad.getId()) != null && validarOdontologoYPaciente(entidad.getPaciente().getId(),entidad.getOdontologo().getId())){
            turnoDto = new TurnoDto(turnoRepository.save(entidad.toEntity()));
            entidad.setOdontologo(odontologoService.buscarPorId(entidad.getOdontologo().getId()).toEntity());
            entidad.setPaciente(pacienteService.buscarPorId(entidad.getPaciente().getId()).toEntity());
        }
        return turnoDto;
    }

    @Override
    public TurnoDto borrar(Long id) {
        TurnoDto turnoDto = buscarPorId(id);
        if (turnoDto != null){
            turnoRepository.deleteById(id);
        }
        return turnoDto;
    }

    public boolean validarOdontologoYPaciente(Long idPaciente, Long idOdontologo){
        PacienteDto pacienteDto = pacienteService.buscarPorId(idPaciente);
        OdontologoDto odontologoDto = odontologoService.buscarPorId(idOdontologo);
        boolean respuesta = false;
        if (pacienteDto != null && odontologoDto != null){
            respuesta = true;
        }
        return respuesta;
    }

    public List<TurnoDto> obtenerTurnosPorDias(Long dias){
        List<TurnoDto> turnosPorDias = new ArrayList<>();
        List<TurnoDto> turnos = obtenerTodos();
        LocalDateTime fechaSolicitud = LocalDateTime.now();
        LocalDateTime fechaLimite = LocalDateTime.now().plusDays(dias + 1);
        for (TurnoDto turno:turnos) {
            if ((turno.getFecha().isAfter(fechaSolicitud) || turno.getFecha().isEqual(fechaSolicitud)) && turno.getFecha().isBefore(fechaLimite)){
                turnosPorDias.add(turno);
            }
        }
        return turnosPorDias;
    }
}
