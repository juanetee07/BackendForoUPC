package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Etiqueta;
import com.upc.demo.repository.resoluciones.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaServicio implements IEtiquetaServicio {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Override
    public Etiqueta guardar(Etiqueta etiqueta) {

        validarEtiqueta(etiqueta);

        if (etiquetaRepository.existsByNombre(
                etiqueta.getNombre())) {

            throw new IllegalArgumentException(
                    "Ya existe una etiqueta con ese nombre."
            );
        }

        return etiquetaRepository.save(etiqueta);
    }

    @Override
    public Etiqueta buscarPorId(Long id) {

        return etiquetaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe una etiqueta con ID: " + id
                        ));
    }

    @Override
    public List<Etiqueta> listarTodos() {

        return etiquetaRepository.findAll();
    }

    @Override
    public Etiqueta actualizar(Long id, Etiqueta datosActualizados) {

        Etiqueta etiqueta = buscarPorId(id);

        validarEtiqueta(datosActualizados);

        etiqueta.setNombre(datosActualizados.getNombre());

        return etiquetaRepository.save(etiqueta);
    }

    @Override
    public void eliminar(Long id) {

        Etiqueta etiqueta = buscarPorId(id);

        etiquetaRepository.delete(etiqueta);
    }

    private void validarEtiqueta(Etiqueta etiqueta) {

        if (etiqueta.getNombre() == null
                || etiqueta.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la etiqueta es obligatorio."
            );
        }
    }
}