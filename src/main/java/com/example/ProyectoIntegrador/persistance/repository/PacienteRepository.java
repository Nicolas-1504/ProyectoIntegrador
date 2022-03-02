package com.example.ProyectoIntegrador.persistance.repository;

import com.example.ProyectoIntegrador.persistance.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
