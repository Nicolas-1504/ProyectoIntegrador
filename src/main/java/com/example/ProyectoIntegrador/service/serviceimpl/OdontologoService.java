package com.example.ProyectoIntegrador.service.serviceimpl;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.persistance.entities.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProyectoIntegrador.persistance.repository.OdontologoRepository;
import com.example.ProyectoIntegrador.service.IService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IService<OdontologoDto> {


    OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public OdontologoDto guardar(OdontologoDto entidad) {
        if (entidad != null){
            Odontologo odontologo = odontologoRepository.save(entidad.toEntity());
            entidad.setId(odontologo.getId());
        }
        return entidad;
    }

    @Override
    public List<OdontologoDto> obtenerTodos() {
        List<OdontologoDto> odontologos = new ArrayList<>();
        for (Odontologo odontologo:odontologoRepository.findAll()) {
            odontologos.add(new OdontologoDto(odontologo));
        }
        return odontologos;
    }

    @Override
    public OdontologoDto buscarPorId(Long id) {
        OdontologoDto odontologoDto = null;
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        if (odontologo != null){
            odontologoDto = new OdontologoDto(odontologo);
        }
        return odontologoDto;
    }

    @Override
    public OdontologoDto actualizar(OdontologoDto entidad) {
        OdontologoDto odontologo = null;
        if (buscarPorId(entidad.getId()) != null){
            odontologo = new OdontologoDto(odontologoRepository.save(entidad.toEntity()));
        }
        return odontologo;
    }

    @Override
    public OdontologoDto borrar(Long id) {
        OdontologoDto odontologo = buscarPorId(id);
        if (odontologo != null){
            odontologoRepository.deleteById(id);
        }
        return odontologo;
    }
}
