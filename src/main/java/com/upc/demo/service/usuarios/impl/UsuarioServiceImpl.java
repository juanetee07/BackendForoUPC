package com.upc.demo.service.usuarios.impl;

import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import com.upc.demo.service.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardar(Usuario usuario) {

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (usuario.getApellido() == null || usuario.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        if (usuario.getDni() == null || usuario.getDni().isBlank()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        if (usuarioRepository.findByDni(usuario.getDni()) != null) {
            throw new RuntimeException("Ya existe un usuario con ese DNI");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return usuario;
    }

    @Override
    public Usuario buscarPorDni(String dni) {

        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }

        Usuario usuario = usuarioRepository.findByDni(dni);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return usuario;
    }

    @Override
    public Usuario modificar(Long id, Usuario usuario) {

        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setDni(usuario.getDni());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminar(Long id) {

        Usuario usuarioExistente = buscarPorId(id);

        usuarioExistente.setEstado("INACTIVO");

        usuarioRepository.save(usuarioExistente);
    }
}