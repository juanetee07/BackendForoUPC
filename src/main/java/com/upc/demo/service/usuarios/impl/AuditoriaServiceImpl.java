package com.upc.demo.service.usuarios.impl;

import com.upc.demo.entity.usuarios.Auditoria;
import com.upc.demo.repository.usuarios.AuditoriaRepository;
import com.upc.demo.service.usuarios.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Override
    public Auditoria guardar(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    @Override
    public Auditoria buscarPorId(Long id) {
        return auditoriaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Auditoria> listar() {
        return auditoriaRepository.findAll();
    }

    @Override
    public List<Auditoria> buscarPorUsuario(Long usuarioId) {
        return auditoriaRepository.findByUsuarioIdUsuario(usuarioId);
    }

    @Override
    public List<Auditoria> buscarPorAccion(String accion) {
        return auditoriaRepository.findByAccion(accion);
    }

    @Override
    public List<Auditoria> buscarPorFecha(LocalDateTime inicio,
                                          LocalDateTime fin) {
        return auditoriaRepository.findByFechaHoraBetween(inicio, fin);
    }
}