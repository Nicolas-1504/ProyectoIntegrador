package com.example.ProyectoIntegrador.service.serviceimpl;

import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.persistance.entities.Paciente;
import com.example.ProyectoIntegrador.persistance.repository.PacienteRepository;
import com.example.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IService<PacienteDto> {

    PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteDto guardar(PacienteDto entidad) {
        if (entidad != null){
            entidad.setFechaIngreso(LocalDate.now());
            Paciente paciente = pacienteRepository.save(entidad.toEntity());
            entidad.setId(paciente.getId());
            entidad.getDomicilio().setId(paciente.getDomicilio().getId());
        }
        return entidad;
    }

    @Override
    public List<PacienteDto> obtenerTodos() {
        List<PacienteDto> pacientes = new ArrayList<>();
        for (Paciente paciente:pacienteRepository.findAll()) {
            pacientes.add(new PacienteDto(paciente));
        }
        return pacientes;
    }

    @Override
    public PacienteDto buscarPorId(Long id) {
        PacienteDto pacienteDto = null;
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null){
            pacienteDto = new PacienteDto(paciente);
        }
        return pacienteDto;
    }

    @Override
    public PacienteDto actualizar(PacienteDto entidad) {
        PacienteDto pacienteDto = null;
        if (buscarPorId(entidad.getId()) != null){
            entidad.setFechaIngreso(LocalDate.now());
            pacienteDto = new PacienteDto(pacienteRepository.save(entidad.toEntity()));
        }
        return pacienteDto;
    }

    @Override
    public PacienteDto borrar(Long id) {
        PacienteDto paciente = buscarPorId(id);
        if (paciente != null){
            pacienteRepository.deleteById(id);
        }
        return paciente;
    }
}
