package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ResolucionEtiquetaRequestDTO;
import com.upc.demo.dto.response.resoluciones.ResolucionEtiquetaResponseDTO;
import com.upc.demo.entity.resoluciones.Etiqueta;
import com.upc.demo.entity.resoluciones.Resolucion;
import com.upc.demo.entity.resoluciones.ResolucionEtiqueta;
import com.upc.demo.repository.resoluciones.EtiquetaRepository;
import com.upc.demo.repository.resoluciones.ResolucionEtiquetaRepository;
import com.upc.demo.repository.resoluciones.ResolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResolucionEtiquetaServicio implements IResolucionEtiquetaServicio {

    @Autowired
    private ResolucionEtiquetaRepository resolucionEtiquetaRepository;

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    private ResolucionEtiquetaResponseDTO toResponseDTO(ResolucionEtiqueta resolucionEtiqueta) {
        if (resolucionEtiqueta == null) {
            return null;
        }
        ResolucionEtiquetaResponseDTO dto = new ResolucionEtiquetaResponseDTO();
        dto.setIdResolucionEtiqueta(resolucionEtiqueta.getIdResolucionEtiqueta());
        if (resolucionEtiqueta.getResolucion() != null) {
            dto.setIdResolucion(resolucionEtiqueta.getResolucion().getIdResolucion());
            dto.setNumeroResolucion(resolucionEtiqueta.getResolucion().getNumeroResolucion());
        }
        if (resolucionEtiqueta.getEtiqueta() != null) {
            dto.setIdEtiqueta(resolucionEtiqueta.getEtiqueta().getIdEtiqueta());
            dto.setNombreEtiqueta(resolucionEtiqueta.getEtiqueta().getNombre());
        }
        return dto;
    }

    private ResolucionEtiqueta buscarEntidadPorId(Long id) {
        return resolucionEtiquetaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No existe una asociación con ID: " + id));
    }

    @Override
    public ResolucionEtiquetaResponseDTO guardar(ResolucionEtiquetaRequestDTO requestDTO) {

        if (requestDTO.getIdResolucion() == null) {
            throw new IllegalArgumentException("Debe asociarse una resolución.");
        }

        if (requestDTO.getIdEtiqueta() == null) {
            throw new IllegalArgumentException("Debe asociarse una etiqueta.");
        }

        Resolucion resolucion = resolucionRepository.findById(requestDTO.getIdResolucion())
                .orElseThrow(() -> new RuntimeException(
                        "No existe una resolución con ID: " + requestDTO.getIdResolucion()));

        Etiqueta etiqueta = etiquetaRepository.findById(requestDTO.getIdEtiqueta())
                .orElseThrow(() -> new RuntimeException(
                        "No existe una etiqueta con ID: " + requestDTO.getIdEtiqueta()));

        ResolucionEtiqueta resolucionEtiqueta = ResolucionEtiqueta.builder()
                .resolucion(resolucion)
                .etiqueta(etiqueta)
                .build();

        ResolucionEtiqueta guardado = resolucionEtiquetaRepository.save(resolucionEtiqueta);
        return toResponseDTO(guardado);
    }

    @Override
    public ResolucionEtiquetaResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public List<ResolucionEtiquetaResponseDTO> listarTodos() {
        return resolucionEtiquetaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        ResolucionEtiqueta resolucionEtiqueta = buscarEntidadPorId(id);
        resolucionEtiquetaRepository.delete(resolucionEtiqueta);
    }
}