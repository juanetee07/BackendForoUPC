package com.upc.demo.service.usuarios;

import com.upc.demo.dto.request.usuarios.UsuarioRequestDTO;
import com.upc.demo.dto.response.usuarios.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO guardar(UsuarioRequestDTO requestDTO);

    UsuarioResponseDTO buscarPorId(Long id);

    List<UsuarioResponseDTO> listar();

    UsuarioResponseDTO buscarPorEmail(String email);

    UsuarioResponseDTO buscarPorDni(String dni);

    UsuarioResponseDTO modificar(Long id, UsuarioRequestDTO requestDTO);

    void eliminar(Long id);
}