package com.upc.demo.service.usuarios.impl;

import com.upc.demo.dto.request.usuarios.RolRequestDTO;
import com.upc.demo.dto.response.usuarios.RolResponseDTO;
import com.upc.demo.entity.usuarios.Rol;
import com.upc.demo.repository.usuarios.RolRepository;
import com.upc.demo.service.usuarios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    private RolResponseDTO toResponseDTO(Rol rol) {
        if (rol == null) {
            return null;
        }
        RolResponseDTO dto = new RolResponseDTO();
        dto.setIdRol(rol.getIdRol());
        dto.setNombre(rol.getNombre());
        return dto;
    }

    @Override
    public RolResponseDTO guardar(RolRequestDTO requestDTO) {

        if (requestDTO.getNombre() == null || requestDTO.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio");
        }

        if (rolRepository.findByNombre(requestDTO.getNombre()) != null) {
            throw new RuntimeException("Ya existe un rol con ese nombre");
        }

        Rol rol = new Rol();
        rol.setNombre(requestDTO.getNombre());

        Rol rolGuardado = rolRepository.save(rol);
        return toResponseDTO(rolGuardado);
    }

    @Override
    public RolResponseDTO buscarPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return toResponseDTO(rol);
    }

    @Override
    public List<RolResponseDTO> listar() {
        return rolRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public RolResponseDTO buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio");
        }

        Rol rol = rolRepository.findByNombre(nombre);
        if (rol == null) {
            throw new RuntimeException("Rol no encontrado");
        }

        return toResponseDTO(rol);
    }

    @Override
    public RolResponseDTO modificar(Long id, RolRequestDTO requestDTO) {
        Rol rolExistente = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (requestDTO.getNombre() == null || requestDTO.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio");
        }

        Rol rolConMismoNombre = rolRepository.findByNombre(requestDTO.getNombre());
        if (rolConMismoNombre != null && !rolConMismoNombre.getIdRol().equals(id)) {
            throw new RuntimeException("Ya existe un rol con ese nombre");
        }

        rolExistente.setNombre(requestDTO.getNombre());

        Rol rolActualizado = rolRepository.save(rolExistente);
        return toResponseDTO(rolActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (rol.getUsuarios() != null && !rol.getUsuarios().isEmpty()) {
            throw new RuntimeException("No se puede eliminar un rol que está asignado a usuarios");
        }

        rolRepository.delete(rol);
    }
}