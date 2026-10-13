package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.CategoriaResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.CategoriaResolucionResponseDTO;

import java.util.List;

public interface ICategoriaResolucionServicio {

    CategoriaResolucionResponseDTO guardar(CategoriaResolucionRequestDTO requestDTO);

    CategoriaResolucionResponseDTO buscarPorId(Long id);

    List<CategoriaResolucionResponseDTO> listarTodos();

    CategoriaResolucionResponseDTO actualizar(Long id, CategoriaResolucionRequestDTO requestDTO);

    void eliminar(Long id);
}