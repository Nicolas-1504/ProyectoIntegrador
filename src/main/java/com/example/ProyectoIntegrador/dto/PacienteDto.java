package com.example.ProyectoIntegrador.dto;

import com.example.ProyectoIntegrador.persistance.entities.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class PacienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioDto domicilio;

    public PacienteDto(){

    }

    public PacienteDto(String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioDto domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteDto(Long id, String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteDto(Paciente paciente){
        this.id = paciente.getId();
        this.nombre = paciente.getNombre();
        this.apellido = paciente.getApellido();
        this.dni = paciente.getDni();
        this.fechaIngreso = paciente.getFechaIngreso();
        this.domicilio = new DomicilioDto(paciente.getDomicilio());
    }

    public Paciente toEntity(){
        Paciente entity = new Paciente();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setDni(dni);
        entity.setFechaIngreso(fechaIngreso);
        entity.setDomicilio(domicilio.toEntity());

        return entity;
    }
}
