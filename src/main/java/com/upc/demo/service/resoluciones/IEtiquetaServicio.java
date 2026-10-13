package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.EtiquetaRequestDTO;
import com.upc.demo.dto.response.resoluciones.EtiquetaResponseDTO;

import java.util.List;

public interface IEtiquetaServicio {

    EtiquetaResponseDTO guardar(EtiquetaRequestDTO requestDTO);

    EtiquetaResponseDTO buscarPorId(Long id);

    List<EtiquetaResponseDTO> listarTodos();

    EtiquetaResponseDTO actualizar(Long id, EtiquetaRequestDTO requestDTO);

    void eliminar(Long id);
}