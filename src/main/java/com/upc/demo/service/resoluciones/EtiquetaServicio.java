package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.EtiquetaRequestDTO;
import com.upc.demo.dto.response.resoluciones.EtiquetaResponseDTO;
import com.upc.demo.entity.resoluciones.Etiqueta;
import com.upc.demo.repository.resoluciones.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaServicio implements IEtiquetaServicio {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    private EtiquetaResponseDTO toResponseDTO(Etiqueta etiqueta) {
        if (etiqueta == null) {
            return null;
        }
        return new EtiquetaResponseDTO(
                etiqueta.getIdEtiqueta(),
                etiqueta.getNombre()
        );
    }

    private Etiqueta buscarEntidadPorId(Long id) {
        return etiquetaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No existe una etiqueta con ID: " + id));
    }

    @Override
    public EtiquetaResponseDTO guardar(EtiquetaRequestDTO requestDTO) {

        validarEtiqueta(requestDTO);

        if (etiquetaRepository.existsByNombre(requestDTO.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe una etiqueta con ese nombre."
            );
        }

        Etiqueta etiqueta = Etiqueta.builder()
                .nombre(requestDTO.getNombre())
                .build();

        Etiqueta guardada = etiquetaRepository.save(etiqueta);
        return toResponseDTO(guardada);
    }

    @Override
    public EtiquetaResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public List<EtiquetaResponseDTO> listarTodos() {
        return etiquetaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public EtiquetaResponseDTO actualizar(Long id, EtiquetaRequestDTO datosActualizados) {

        Etiqueta etiqueta = buscarEntidadPorId(id);

        validarEtiqueta(datosActualizados);

        if (!etiqueta.getNombre().equalsIgnoreCase(datosActualizados.getNombre())
                && etiquetaRepository.existsByNombre(datosActualizados.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe una etiqueta con ese nombre."
            );
        }

        etiqueta.setNombre(datosActualizados.getNombre());

        Etiqueta actualizada = etiquetaRepository.save(etiqueta);
        return toResponseDTO(actualizada);
    }

    @Override
    public void eliminar(Long id) {

        Etiqueta etiqueta = buscarEntidadPorId(id);

        etiquetaRepository.delete(etiqueta);
    }

    private void validarEtiqueta(EtiquetaRequestDTO etiqueta) {

        if (etiqueta.getNombre() == null
                || etiqueta.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la etiqueta es obligatorio."
            );
        }
    }
}