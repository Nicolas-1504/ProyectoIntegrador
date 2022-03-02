package com.example.ProyectoIntegrador.dto;

import com.example.ProyectoIntegrador.persistance.entities.Odontologo;
import com.example.ProyectoIntegrador.persistance.entities.Paciente;
import com.example.ProyectoIntegrador.persistance.entities.Turno;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TurnoDto {

    private Long id;
    private LocalDateTime fecha;
    private Paciente paciente;
    private Odontologo odontologo;

    public TurnoDto() {
    }

    public TurnoDto(LocalDateTime fecha, Paciente paciente, Odontologo odontologo) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public TurnoDto(Long id, LocalDateTime fecha, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public TurnoDto(Turno turno){
        this.id = turno.getId();
        this.fecha = turno.getFecha();
        this.paciente = turno.getPaciente();
        this.odontologo = turno.getOdontologo();
    }

    public Turno toEntity(){
        Turno entity = new Turno();

        entity.setId(id);
        entity.setFecha(fecha);
        entity.setPaciente(paciente);
        entity.setOdontologo(odontologo);

        return entity;
    }
}
