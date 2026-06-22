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
        return recuperacionPasswordRepository.save(recuperacionPassword);
    }

    @Override
    public RecuperacionPassword buscarPorId(Long id) {
        return recuperacionPasswordRepository.findById(id).orElse(null);
    }

    @Override
    public List<RecuperacionPassword> listar() {
        return recuperacionPasswordRepository.findAll();
    }

    @Override
    public RecuperacionPassword buscarPorToken(String token) {
        return recuperacionPasswordRepository.findByToken(token);
    }

    @Override
    public RecuperacionPassword buscarPorUsuario(Long usuarioId) {
        return recuperacionPasswordRepository.findByUsuarioIdUsuario(usuarioId);
    }

    @Override
    public void eliminar(Long id) {
        recuperacionPasswordRepository.deleteById(id);
    }
}