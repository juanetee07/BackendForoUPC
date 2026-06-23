package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Carrera;

import java.util.List;

public interface ICarreraServicio {

    Carrera guardar(Carrera carrera);

    Carrera buscarPorId(Long id);

    List<Carrera> listarTodos();

    Carrera actualizar(Long id, Carrera carrera);

    void eliminar(Long id);
}