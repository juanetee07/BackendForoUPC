package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ComentarioRequestDTO;
import com.upc.demo.dto.response.resoluciones.ComentarioResponseDTO;

import java.util.List;

public interface IComentarioServicio {

    ComentarioResponseDTO guardar(ComentarioRequestDTO requestDTO);

    ComentarioResponseDTO buscarPorId(Long id);

    List<ComentarioResponseDTO> listarTodos();

    ComentarioResponseDTO actualizar(Long id, ComentarioRequestDTO requestDTO);

    void eliminar(Long id);
}