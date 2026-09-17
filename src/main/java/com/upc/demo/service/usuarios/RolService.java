package com.upc.demo.service.usuarios;

import com.upc.demo.dto.request.usuarios.RolRequestDTO;
import com.upc.demo.dto.response.usuarios.RolResponseDTO;

import java.util.List;

public interface RolService {

    RolResponseDTO guardar(RolRequestDTO requestDTO);

    RolResponseDTO buscarPorId(Long id);

    List<RolResponseDTO> listar();

    RolResponseDTO buscarPorNombre(String nombre);

    RolResponseDTO modificar(Long id, RolRequestDTO requestDTO);

    void eliminar(Long id);
}