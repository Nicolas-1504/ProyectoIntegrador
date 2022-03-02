package com.example.ProyectoIntegrador.persistance.repository;

import com.example.ProyectoIntegrador.persistance.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
