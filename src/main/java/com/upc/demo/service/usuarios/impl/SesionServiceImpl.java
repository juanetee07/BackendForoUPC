package com.upc.demo.service.usuarios.impl;

import com.upc.demo.dto.request.usuarios.SesionRequestDTO;
import com.upc.demo.dto.response.usuarios.SesionResponseDTO;
import com.upc.demo.entity.usuarios.Sesion;
import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.usuarios.SesionRepository;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import com.upc.demo.service.usuarios.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SesionServiceImpl implements SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private SesionResponseDTO toResponseDTO(Sesion sesion) {
        if (sesion == null) {
            return null;
        }
        SesionResponseDTO dto = new SesionResponseDTO();
        dto.setIdSesion(sesion.getIdSesion());
        dto.setTokenJwt(sesion.getTokenJwt());
        dto.setFechaInicio(sesion.getFechaInicio());
        dto.setFechaExpiracion(sesion.getFechaExpiracion());
        dto.setActiva(sesion.getActiva());

        if (sesion.getUsuario() != null) {
            dto.setIdUsuario(sesion.getUsuario().getIdUsuario());
            dto.setEmailUsuario(sesion.getUsuario().getEmail());
        }

        return dto;
    }

    @Override
    public SesionResponseDTO guardar(SesionRequestDTO requestDTO) {

        if (requestDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("El idUsuario es obligatorio");
        }

        Usuario usuario = usuarioRepository.findById(requestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Sesion sesion = new Sesion();
        sesion.setUsuario(usuario);
        sesion.setFechaInicio(LocalDateTime.now());
        sesion.setActiva(true);

        if (requestDTO.getFechaExpiracion() != null) {
            sesion.setFechaExpiracion(requestDTO.getFechaExpiracion());
        } else {
            sesion.setFechaExpiracion(LocalDateTime.now().plusHours(24));
        }

        sesion.setTokenJwt(UUID.randomUUID().toString());

        Sesion sesionGuardada = sesionRepository.save(sesion);
        return toResponseDTO(sesionGuardada);
    }

    @Override
    public SesionResponseDTO buscarPorId(Long id) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));
        return toResponseDTO(sesion);
    }

    @Override
    public List<SesionResponseDTO> listar() {
        return sesionRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<SesionResponseDTO> listarPorUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("El id del usuario es obligatorio");
        }

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return sesionRepository.findByUsuarioIdUsuario(usuarioId).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<SesionResponseDTO> listarActivas() {
        return sesionRepository.findByActiva(true).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));

        sesion.setActiva(false);

        sesionRepository.save(sesion);
    }
}