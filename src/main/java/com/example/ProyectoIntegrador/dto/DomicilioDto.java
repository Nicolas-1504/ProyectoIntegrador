package com.example.ProyectoIntegrador.dto;

import com.example.ProyectoIntegrador.persistance.entities.Domicilio;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DomicilioDto {

    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public DomicilioDto(){

    }

    public DomicilioDto(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public DomicilioDto(Long id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public DomicilioDto(Domicilio domicilio) {
        this.id = domicilio.getId();
        this.calle = domicilio.getCalle();
        this.numero = domicilio.getNumero();
        this.localidad = domicilio.getLocalidad();
        this.provincia = domicilio.getProvincia();
    }

    public Domicilio toEntity() {
        Domicilio entity = new Domicilio();

        entity.setId(id);
        entity.setCalle(calle);
        entity.setNumero(numero);
        entity.setLocalidad(localidad);
        entity.setProvincia(provincia);
        return entity;
    }
}
