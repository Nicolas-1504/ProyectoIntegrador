package com.example.ProyectoIntegrador.dto;

import com.example.ProyectoIntegrador.persistance.entities.Odontologo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OdontologoDto {

    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public OdontologoDto(Long id,String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public OdontologoDto(Odontologo odontologo) {
        this.id = odontologo.getId();
        this.nombre = odontologo.getNombre();
        this.apellido = odontologo.getApellido();
        this.matricula = odontologo.getMatricula();
    }

    public Odontologo toEntity() {
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setMatricula(matricula);
        return entity;
    }
}
