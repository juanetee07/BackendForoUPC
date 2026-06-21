package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.CategoriaResolucion;

import java.util.List;

public interface ICategoriaResolucionServicio {

    CategoriaResolucion guardar(CategoriaResolucion categoria);

    CategoriaResolucion buscarPorId(Long id);

    List<CategoriaResolucion> listarTodos();

    CategoriaResolucion actualizar(Long id, CategoriaResolucion categoria);

    void eliminar(Long id);
}