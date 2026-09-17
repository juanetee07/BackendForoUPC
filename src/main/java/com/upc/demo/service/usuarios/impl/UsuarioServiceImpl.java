package com.upc.demo.service.usuarios.impl;

import com.upc.demo.dto.request.usuarios.UsuarioRequestDTO;
import com.upc.demo.dto.response.usuarios.UsuarioResponseDTO;
import com.upc.demo.entity.usuarios.Rol;
import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.usuarios.RolRepository;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import com.upc.demo.service.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setDni(usuario.getDni());
        dto.setEmail(usuario.getEmail());
        dto.setEstado(usuario.getEstado());
        dto.setEstadoSolicitud(usuario.getEstadoSolicitud());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        dto.setUltimoAcceso(usuario.getUltimoAcceso());

        if (usuario.getRol() != null) {
            dto.setIdRol(usuario.getRol().getIdRol());
            dto.setNombreRol(usuario.getRol().getNombre());
        } else {
            dto.setIdRol(null);
            dto.setNombreRol(null);
        }

        return dto;
    }

    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO requestDTO) {

        if (requestDTO.getNombre() == null || requestDTO.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (requestDTO.getApellido() == null || requestDTO.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }

        if (requestDTO.getEmail() == null || requestDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        if (requestDTO.getDni() == null || requestDTO.getDni().isBlank()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }

        if (requestDTO.getContraseña() == null || requestDTO.getContraseña().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        if (usuarioRepository.findByEmail(requestDTO.getEmail()) != null) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        if (usuarioRepository.findByDni(requestDTO.getDni()) != null) {
            throw new RuntimeException("Ya existe un usuario con ese DNI");
        }

        if (requestDTO.getIdRol() == null) {
            throw new RuntimeException("Rol no encontrado");
        }

        Rol rol = rolRepository.findById(requestDTO.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombre(requestDTO.getNombre());
        usuario.setApellido(requestDTO.getApellido());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setDni(requestDTO.getDni());
        usuario.setContraseña(requestDTO.getContraseña());
        usuario.setRol(rol);

        if (requestDTO.getEstado() == null || requestDTO.getEstado().isBlank()) {
            usuario.setEstado("ACTIVO");
        } else {
            usuario.setEstado(requestDTO.getEstado());
        }

        usuario.setFechaRegistro(LocalDateTime.now());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return toResponseDTO(usuarioGuardado);
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toResponseDTO(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public UsuarioResponseDTO buscarPorEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return toResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO buscarPorDni(String dni) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }

        Usuario usuario = usuarioRepository.findByDni(dni);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return toResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO modificar(Long id, UsuarioRequestDTO requestDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(requestDTO.getNombre());
        usuarioExistente.setApellido(requestDTO.getApellido());
        usuarioExistente.setEmail(requestDTO.getEmail());
        usuarioExistente.setDni(requestDTO.getDni());

        if (requestDTO.getIdRol() != null) {
            Rol rol = rolRepository.findById(requestDTO.getIdRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuarioExistente.setRol(rol);
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return toResponseDTO(usuarioActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setEstado("INACTIVO");

        usuarioRepository.save(usuarioExistente);
    }
}