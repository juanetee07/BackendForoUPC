package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Resolucion;

import java.util.List;

public interface IResolucionServicio {

    Resolucion guardar(Resolucion resolucion);

    Resolucion buscarPorId(Long id);

    List<Resolucion> listarTodos();

    Resolucion actualizar(Long id, Resolucion resolucion);

    void eliminar(Long id);
}