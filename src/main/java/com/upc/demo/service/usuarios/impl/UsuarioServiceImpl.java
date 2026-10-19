package com.upc.demo.service.usuarios.impl;

import com.upc.demo.dto.request.usuarios.LoginRequestDTO;
import com.upc.demo.dto.request.usuarios.UsuarioRequestDTO;
import com.upc.demo.dto.response.usuarios.AuthResponseDTO;
import com.upc.demo.dto.response.usuarios.UsuarioResponseDTO;
import com.upc.demo.entity.usuarios.Rol;
import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.usuarios.RolRepository;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import com.upc.demo.security.JwtService;
import com.upc.demo.service.usuarios.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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
        }

        return dto;
    }

    // GUARDAR USUARIO
    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO requestDTO) {

        validarDatosUsuario(requestDTO);

        String email = requestDTO.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        String dni = requestDTO.getDni().trim();

        if (usuarioRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un usuario con ese email"
            );
        }

        if (usuarioRepository.findByDni(dni) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un usuario con ese DNI"
            );
        }

        if (requestDTO.getIdRol() == null) {
            throw new IllegalArgumentException(
                    "El rol es obligatorio"
            );
        }

        Rol rol = rolRepository.findById(requestDTO.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró el rol solicitado"
                ));

        Usuario usuario = new Usuario();
        usuario.setNombre(requestDTO.getNombre().trim());
        usuario.setApellido(requestDTO.getApellido().trim());
        usuario.setEmail(email);
        usuario.setDni(dni);
        usuario.setContraseña(
                passwordEncoder.encode(requestDTO.getContraseña())
        );
        usuario.setRol(rol);

        String estado = requestDTO.getEstado();
        usuario.setEstado(
                estado == null || estado.isBlank()
                        ? "ACTIVO"
                        : estado.trim().toUpperCase(Locale.ROOT)
        );

        usuario.setFechaRegistro(LocalDateTime.now());

        Usuario guardado = usuarioRepository.save(usuario);
        return toResponseDTO(guardado);
    }

    // BUSCAR POR ID USUARIO
    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Usuario no encontrado"
                        )
                );

        return toResponseDTO(usuario);
    }

    // LISTAR USUARIOS
    @Override
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // BUSCAR POR EMAIL
    @Override
    public UsuarioResponseDTO buscarPorEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "El email es obligatorio"
            );
        }

        Usuario usuario = usuarioRepository.findByEmail(
                email.trim().toLowerCase(Locale.ROOT)
        );

        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Usuario no encontrado"
            );
        }

        return toResponseDTO(usuario);
    }

    // BUSCAR POR DNI
    @Override
    public UsuarioResponseDTO buscarPorDni(String dni) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException(
                    "El DNI es obligatorio"
            );
        }

        Usuario usuario = usuarioRepository.findByDni(dni.trim());

        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Usuario no encontrado"
            );
        }

        return toResponseDTO(usuario);
    }

    // MODIFICAR USUARIO
    @Override
    public UsuarioResponseDTO modificar(
            Long id,
            UsuarioRequestDTO requestDTO
    ) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Usuario no encontrado"
                        )
                );

        if (requestDTO.getNombre() != null
                && !requestDTO.getNombre().isBlank()) {
            usuario.setNombre(requestDTO.getNombre().trim());
        }

        if (requestDTO.getApellido() != null
                && !requestDTO.getApellido().isBlank()) {
            usuario.setApellido(requestDTO.getApellido().trim());
        }

        if (requestDTO.getEmail() != null
                && !requestDTO.getEmail().isBlank()) {

            String email = requestDTO.getEmail()
                    .trim()
                    .toLowerCase(Locale.ROOT);

            Usuario existente = usuarioRepository.findByEmail(email);

            if (existente != null
                    && !existente.getIdUsuario().equals(id)) {
                throw new IllegalArgumentException(
                        "Ese email ya pertenece a otro usuario"
                );
            }

            usuario.setEmail(email);
        }

        if (requestDTO.getDni() != null
                && !requestDTO.getDni().isBlank()) {

            String dni = requestDTO.getDni().trim();
            Usuario existente = usuarioRepository.findByDni(dni);

            if (existente != null
                    && !existente.getIdUsuario().equals(id)) {
                throw new IllegalArgumentException(
                        "Ese DNI ya pertenece a otro usuario"
                );
            }

            usuario.setDni(dni);
        }

        if (requestDTO.getIdRol() != null) {
            Rol rol = rolRepository.findById(requestDTO.getIdRol())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Rol no encontrado"
                            )
                    );

            usuario.setRol(rol);
        }

        Usuario actualizado = usuarioRepository.save(usuario);
        return toResponseDTO(actualizado);
    }

    // ELIMINAR USUARIO
    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Usuario no encontrado"
                        )
                );

        usuario.setEstado("INACTIVO");
        usuarioRepository.save(usuario);
    }

    // REGISTRAR USUARIO
    @Override
    @Transactional
    public UsuarioResponseDTO registrar(
            UsuarioRequestDTO requestDTO
    ) {
        validarDatosUsuario(requestDTO);

        String email = requestDTO.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        String dni = requestDTO.getDni().trim();

        if (usuarioRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un usuario con ese email"
            );
        }

        if (usuarioRepository.findByDni(dni) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un usuario con ese DNI"
            );
        }

        Rol rolEstudiante = rolRepository.findByNombre("ESTUDIANTE")
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No existe el rol ESTUDIANTE en la base de datos"
                        )
                );

        Usuario usuario = new Usuario();
        usuario.setNombre(requestDTO.getNombre().trim());
        usuario.setApellido(requestDTO.getApellido().trim());
        usuario.setDni(dni);
        usuario.setEmail(email);
        usuario.setContraseña(
                passwordEncoder.encode(requestDTO.getContraseña())
        );
        usuario.setRol(rolEstudiante);
        usuario.setEstado("ACTIVO");
        usuario.setFechaRegistro(LocalDateTime.now());

        Usuario guardado = usuarioRepository.save(usuario);
        return toResponseDTO(guardado);
    }

    // LOGIN USUARIO
    @Override
    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        if (requestDTO.getEmail() == null
                || requestDTO.getEmail().isBlank()
                || requestDTO.getContraseña() == null
                || requestDTO.getContraseña().isBlank()) {

            throw new IllegalArgumentException(
                    "El email y la contraseña son obligatorios"
            );
        }

        String email = requestDTO.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                email,
                                requestDTO.getContraseña()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Credenciales inválidas"
            );
        }

        usuario.setUltimoAcceso(LocalDateTime.now());
        Usuario actualizado = usuarioRepository.save(usuario);

        String token = jwtService.generarToken(userDetails);

        return new AuthResponseDTO(
                token,
                toResponseDTO(actualizado)
        );
    }

    // VALIDAR DATOS DE USUARIOS
    private void validarDatosUsuario(UsuarioRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException(
                    "Los datos del usuario son obligatorios"
            );
        }

        if (requestDTO.getNombre() == null
                || requestDTO.getNombre().isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio"
            );
        }

        if (requestDTO.getApellido() == null
                || requestDTO.getApellido().isBlank()) {
            throw new IllegalArgumentException(
                    "El apellido es obligatorio"
            );
        }

        if (requestDTO.getDni() == null
                || requestDTO.getDni().isBlank()) {
            throw new IllegalArgumentException(
                    "El DNI es obligatorio"
            );
        }

        if (requestDTO.getEmail() == null
                || requestDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "El email es obligatorio"
            );
        }

        if (requestDTO.getContraseña() == null
                || requestDTO.getContraseña().isBlank()) {
            throw new IllegalArgumentException(
                    "La contraseña es obligatoria"
            );
        }
    }
}
