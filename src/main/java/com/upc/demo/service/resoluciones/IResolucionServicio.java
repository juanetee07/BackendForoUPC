package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.ResolucionResponseDTO;

import java.util.List;

public interface IResolucionServicio {

    ResolucionResponseDTO guardar(ResolucionRequestDTO requestDTO);

    ResolucionResponseDTO buscarPorId(Long id);

    List<ResolucionResponseDTO> listarTodos();

    ResolucionResponseDTO actualizar(Long id, ResolucionRequestDTO requestDTO);

    void eliminar(Long id);
}