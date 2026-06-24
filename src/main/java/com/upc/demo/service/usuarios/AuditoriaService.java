package com.upc.demo.service.usuarios;

import com.upc.demo.entity.usuarios.Auditoria;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaService {

    Auditoria guardar(Auditoria auditoria);

    Auditoria buscarPorId(Long id);

    List<Auditoria> listar();

    List<Auditoria> buscarPorUsuario(Long usuarioId);

    List<Auditoria> buscarPorAccion(String accion);

    List<Auditoria> buscarPorFecha(LocalDateTime inicio,
                                   LocalDateTime fin);

}