package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.ResolucionEtiqueta;

import java.util.List;

public interface IResolucionEtiquetaServicio {

    ResolucionEtiqueta guardar(ResolucionEtiqueta resolucionEtiqueta);

    ResolucionEtiqueta buscarPorId(Long id);

    List<ResolucionEtiqueta> listarTodos();

    void eliminar(Long id);
}