package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Comentario;

import java.util.List;

public interface IComentarioServicio {

    Comentario guardar(Comentario comentario);

    Comentario buscarPorId(Long id);

    List<Comentario> listarTodos();

    Comentario actualizar(Long id, Comentario comentario);

    void eliminar(Long id);
}