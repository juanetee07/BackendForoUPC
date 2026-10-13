package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ResolucionEtiquetaRequestDTO;
import com.upc.demo.dto.response.resoluciones.ResolucionEtiquetaResponseDTO;

import java.util.List;

public interface IResolucionEtiquetaServicio {

    ResolucionEtiquetaResponseDTO guardar(ResolucionEtiquetaRequestDTO requestDTO);

    ResolucionEtiquetaResponseDTO buscarPorId(Long id);

    List<ResolucionEtiquetaResponseDTO> listarTodos();

    void eliminar(Long id);
}