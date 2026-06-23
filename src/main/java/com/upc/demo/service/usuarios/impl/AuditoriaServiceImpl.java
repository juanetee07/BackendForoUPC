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

        if (auditoria.getUsuario() == null) {
            throw new IllegalArgumentException("Debe existir un usuario asociado");
        }

        if (auditoria.getEntidad() == null || auditoria.getEntidad().isBlank()) {
            throw new IllegalArgumentException("La entidad es obligatoria");
        }

        if (auditoria.getAccion() == null || auditoria.getAccion().isBlank()) {
            throw new IllegalArgumentException("La acción es obligatoria");
        }

        if (auditoria.getFechaHora() == null) {
            throw new IllegalArgumentException("La fecha y hora es obligatoria");
        }

        return auditoriaRepository.save(auditoria);
    }

    @Override
    public Auditoria buscarPorId(Long id) {

        return auditoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Auditoría no encontrada"));
    }

    @Override
    public List<Auditoria> listar() {
        return auditoriaRepository.findAll();
    }

    @Override
    public List<Auditoria> buscarPorUsuario(Long usuarioId) {

        if (usuarioId == null) {
            throw new IllegalArgumentException("El id del usuario es obligatorio");
        }

        return auditoriaRepository.findByUsuarioIdUsuario(usuarioId);
    }

    @Override
    public List<Auditoria> buscarPorAccion(String accion) {

        if (accion == null || accion.isBlank()) {
            throw new IllegalArgumentException("La acción es obligatoria");
        }

        return auditoriaRepository.findByAccion(accion);
    }

    @Override
    public List<Auditoria> buscarPorFecha(LocalDateTime inicio,
                                          LocalDateTime fin) {

        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias");
        }

        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final");
        }

        return auditoriaRepository.findByFechaHoraBetween(inicio, fin);
    }
}