package com.upc.demo.service.usuarios;

import com.upc.demo.dto.request.usuarios.SesionRequestDTO;
import com.upc.demo.dto.response.usuarios.SesionResponseDTO;

import java.util.List;

public interface SesionService {

    SesionResponseDTO guardar(SesionRequestDTO requestDTO);

    SesionResponseDTO buscarPorId(Long id);

    List<SesionResponseDTO> listar();

    List<SesionResponseDTO> listarPorUsuario(Long usuarioId);

    List<SesionResponseDTO> listarActivas();

    void eliminar(Long id);
}