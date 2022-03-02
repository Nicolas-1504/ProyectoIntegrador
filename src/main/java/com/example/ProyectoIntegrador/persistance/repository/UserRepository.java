package com.example.ProyectoIntegrador.persistance.repository;

import com.example.ProyectoIntegrador.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.name = :name")
    Optional<User> getUserByName(@Param("name") String name);
}
