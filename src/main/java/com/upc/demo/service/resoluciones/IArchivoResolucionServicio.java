package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.ArchivoResolucion;

import java.util.List;

public interface IArchivoResolucionServicio {

    ArchivoResolucion guardar(ArchivoResolucion archivo);

    ArchivoResolucion buscarPorId(Long id);

    List<ArchivoResolucion> listarTodos();

    ArchivoResolucion actualizar(Long id, ArchivoResolucion archivo);

    void eliminar(Long id);
}