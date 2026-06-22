package com.upc.demo.service.usuarios.impl;

import com.upc.demo.entity.usuarios.Sesion;
import com.upc.demo.repository.usuarios.SesionRepository;
import com.upc.demo.service.usuarios.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionServiceImpl implements SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    @Override
    public Sesion guardar(Sesion sesion) {
        return sesionRepository.save(sesion);
    }

    @Override
    public Sesion buscarPorId(Long id) {
        return sesionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Sesion> listar() {
        return sesionRepository.findAll();
    }

    @Override
    public List<Sesion> buscarPorUsuario(Long usuarioId) {
        return sesionRepository.findByUsuarioIdUsuario(usuarioId);
    }

    @Override
    public List<Sesion> buscarPorActiva(Boolean activa) {
        return sesionRepository.findByActiva(activa);
    }

    @Override
    public Sesion modificar(Long id, Sesion sesion) {
        Sesion existente = sesionRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setUsuario(sesion.getUsuario());
            existente.setTokenJwt(sesion.getTokenJwt());
            existente.setFechaInicio(sesion.getFechaInicio());
            existente.setFechaExpiracion(sesion.getFechaExpiracion());
            existente.setActiva(sesion.getActiva());

            return sesionRepository.save(existente);
        }

        return null;
    }

    @Override
    public void eliminar(Long id) {
        sesionRepository.deleteById(id);
    }
}