package com.example.ProyectoIntegrador.persistance.repository;

import com.example.ProyectoIntegrador.persistance.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
