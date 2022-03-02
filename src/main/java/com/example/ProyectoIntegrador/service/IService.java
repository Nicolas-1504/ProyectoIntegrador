package com.example.ProyectoIntegrador.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<E> {

    E guardar(E entidad);
    List<E> obtenerTodos();
    E buscarPorId(Long id);
    E actualizar(E entidad);
    E borrar(Long id);

}
