package com.example.ProyectoIntegrador.service.serviceimpl;

import com.example.ProyectoIntegrador.dto.DomicilioDto;
import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.persistance.entities.Domicilio;
import com.example.ProyectoIntegrador.persistance.repository.DomicilioRepository;
import com.example.ProyectoIntegrador.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomicilioService implements IService<DomicilioDto> {

    @Autowired
    DomicilioRepository domicilioRepository;

    @Override
    public DomicilioDto guardar(DomicilioDto entidad) {
        if (entidad != null){
            Domicilio domicilio = domicilioRepository.save(entidad.toEntity());
            entidad.setId(domicilio.getId());
        }
        return entidad;
    }

    @Override
    public List<DomicilioDto> obtenerTodos() {
        List<DomicilioDto> domicilios = new ArrayList<>();
        for (Domicilio domicilio:domicilioRepository.findAll()) {
            domicilios.add(new DomicilioDto(domicilio));
        }
        return domicilios;
    }

    @Override
    public DomicilioDto buscarPorId(Long id) {
        DomicilioDto domicilioDto = null;
        Domicilio domicilio = domicilioRepository.findById(id).orElse(null);
        if (domicilio != null){
            domicilioDto = new DomicilioDto(domicilio);
        }
        return domicilioDto;
    }

    @Override
    public DomicilioDto actualizar(DomicilioDto entidad) {
        DomicilioDto domicilioDto = null;
        if (buscarPorId(entidad.getId()) != null){
            domicilioDto = new DomicilioDto(domicilioRepository.save(entidad.toEntity()));
        }
        return domicilioDto;
    }

    @Override
    public DomicilioDto borrar(Long id) {
        DomicilioDto domicilio = buscarPorId(id);
        if (domicilio != null){
            domicilioRepository.deleteById(id);
        }
        return domicilio;
    }
}
