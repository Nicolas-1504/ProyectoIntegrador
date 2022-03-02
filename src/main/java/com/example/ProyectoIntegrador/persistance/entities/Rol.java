package com.example.ProyectoIntegrador.persistance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Getter @Setter
public class Rol {

    @Id
    @SequenceGenerator(name = "rol_sequence", sequenceName = "rol_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_sequence")
    private Long id;
    private String name;
}
