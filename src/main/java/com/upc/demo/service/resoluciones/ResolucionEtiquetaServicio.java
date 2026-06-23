package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.ResolucionEtiqueta;
import com.upc.demo.repository.resoluciones.ResolucionEtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResolucionEtiquetaServicio implements IResolucionEtiquetaServicio {

    @Autowired
    private ResolucionEtiquetaRepository resolucionEtiquetaRepository;

    @Override
    public ResolucionEtiqueta guardar(ResolucionEtiqueta resolucionEtiqueta) {

        if (resolucionEtiqueta.getResolucion() == null) {
            throw new IllegalArgumentException(
                    "Debe asociarse una resolución."
            );
        }

        if (resolucionEtiqueta.getEtiqueta() == null) {
            throw new IllegalArgumentException(
                    "Debe asociarse una etiqueta."
            );
        }

        return resolucionEtiquetaRepository.save(resolucionEtiqueta);
    }

    @Override
    public ResolucionEtiqueta buscarPorId(Long id) {

        return resolucionEtiquetaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe una asociación con ID: " + id
                        ));
    }

    @Override
    public List<ResolucionEtiqueta> listarTodos() {

        return resolucionEtiquetaRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {

        ResolucionEtiqueta resolucionEtiqueta = buscarPorId(id);

        resolucionEtiquetaRepository.delete(resolucionEtiqueta);
    }
}