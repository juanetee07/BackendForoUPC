package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ArchivoResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.ArchivoResolucionResponseDTO;

import java.util.List;

public interface IArchivoResolucionServicio {

    ArchivoResolucionResponseDTO guardar(ArchivoResolucionRequestDTO requestDTO);

    ArchivoResolucionResponseDTO buscarPorId(Long id);

    List<ArchivoResolucionResponseDTO> listarTodos();

    ArchivoResolucionResponseDTO actualizar(Long id, ArchivoResolucionRequestDTO requestDTO);

    void eliminar(Long id);
}