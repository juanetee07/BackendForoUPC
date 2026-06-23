package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Etiqueta;

import java.util.List;

public interface IEtiquetaServicio {

    Etiqueta guardar(Etiqueta etiqueta);

    Etiqueta buscarPorId(Long id);

    List<Etiqueta> listarTodos();

    Etiqueta actualizar(Long id, Etiqueta etiqueta);

    void eliminar(Long id);
}