package com.upc.demo.service.usuarios.impl;

import com.upc.demo.entity.usuarios.RecuperacionPassword;
import com.upc.demo.repository.usuarios.RecuperacionPasswordRepository;
import com.upc.demo.service.usuarios.RecuperacionPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecuperacionPasswordServiceImpl implements RecuperacionPasswordService {

    @Autowired
    private RecuperacionPasswordRepository recuperacionPasswordRepository;

    @Override
    public RecuperacionPassword guardar(RecuperacionPassword recuperacionPassword) {

        if (recuperacionPassword.getUsuario() == null) {
            throw new IllegalArgumentException("Debe existir un usuario asociado");
        }

        if (recuperacionPassword.getToken() == null ||
                recuperacionPassword.getToken().isBlank()) {
            throw new IllegalArgumentException("El token es obligatorio");
        }

        if (recuperacionPasswordRepository.findByToken(recuperacionPassword.getToken()) != null) {
            throw new RuntimeException("El token ya existe");
        }

        return recuperacionPasswordRepository.save(recuperacionPassword);
    }

    @Override
    public RecuperacionPassword buscarPorId(Long id) {

        return recuperacionPasswordRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Solicitud de recuperación no encontrada"));
    }

    @Override
    public List<RecuperacionPassword> listar() {
        return recuperacionPasswordRepository.findAll();
    }

    @Override
    public RecuperacionPassword buscarPorToken(String token) {

        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("El token es obligatorio");
        }

        RecuperacionPassword recuperacion = recuperacionPasswordRepository.findByToken(token);

        if (recuperacion == null) {
            throw new RuntimeException("Token no encontrado");
        }

        return recuperacion;
    }

    @Override
    public RecuperacionPassword buscarPorUsuario(Long usuarioId) {

        if (usuarioId == null) {
            throw new IllegalArgumentException("El id del usuario es obligatorio");
        }

        RecuperacionPassword recuperacion = recuperacionPasswordRepository.findByUsuarioIdUsuario(usuarioId);

        if (recuperacion == null) {
            throw new RuntimeException("No existe una solicitud de recuperación para ese usuario");
        }

        return recuperacion;
    }

    @Override
    public void eliminar(Long id) {

        buscarPorId(id);

        recuperacionPasswordRepository.deleteById(id);
    }
}