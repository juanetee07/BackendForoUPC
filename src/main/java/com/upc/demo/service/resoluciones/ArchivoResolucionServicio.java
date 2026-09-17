package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ArchivoResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.ArchivoResolucionResponseDTO;
import com.upc.demo.entity.resoluciones.ArchivoResolucion;
import com.upc.demo.entity.resoluciones.Resolucion;
import com.upc.demo.repository.resoluciones.ArchivoResolucionRepository;
import com.upc.demo.repository.resoluciones.ResolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoResolucionServicio
        implements IArchivoResolucionServicio {

    @Autowired
    private ArchivoResolucionRepository archivoRepository;

    @Autowired
    private ResolucionRepository resolucionRepository;

    private ArchivoResolucionResponseDTO toResponseDTO(ArchivoResolucion archivo) {
        if (archivo == null) {
            return null;
        }
        ArchivoResolucionResponseDTO dto = new ArchivoResolucionResponseDTO();
        dto.setIdArchivo(archivo.getIdArchivo());
        if (archivo.getResolucion() != null) {
            dto.setIdResolucion(archivo.getResolucion().getIdResolucion());
            dto.setNumeroResolucion(archivo.getResolucion().getNumeroResolucion());
        }
        dto.setNombre(archivo.getNombre());
        dto.setUrl(archivo.getUrl());
        dto.setTipo(archivo.getTipo());
        return dto;
    }

    private ArchivoResolucion buscarEntidadPorId(Long id) {
        return archivoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No existe un archivo con ID: " + id));
    }

    @Override
    public ArchivoResolucionResponseDTO guardar(
            ArchivoResolucionRequestDTO requestDTO) {

        validarArchivo(requestDTO);

        if (archivoRepository.existsByNombre(
                requestDTO.getNombre())) {

            throw new IllegalArgumentException(
                    "Ya existe un archivo con ese nombre."
            );
        }

        Resolucion resolucion = resolucionRepository.findById(requestDTO.getIdResolucion())
                .orElseThrow(() -> new RuntimeException(
                        "No existe una resolución con ID: " + requestDTO.getIdResolucion()));

        ArchivoResolucion archivo = ArchivoResolucion.builder()
                .resolucion(resolucion)
                .nombre(requestDTO.getNombre())
                .url(requestDTO.getUrl())
                .tipo(requestDTO.getTipo())
                .build();

        ArchivoResolucion guardado = archivoRepository.save(archivo);
        return toResponseDTO(guardado);
    }

    @Override
    public ArchivoResolucionResponseDTO buscarPorId(Long id) {

        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public List<ArchivoResolucionResponseDTO> listarTodos() {

        return archivoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ArchivoResolucionResponseDTO actualizar(
            Long id,
            ArchivoResolucionRequestDTO datosActualizados) {

        ArchivoResolucion archivo = buscarEntidadPorId(id);

        if (datosActualizados.getIdResolucion() != null) {
            Resolucion resolucion = resolucionRepository.findById(datosActualizados.getIdResolucion())
                    .orElseThrow(() -> new RuntimeException(
                            "No existe una resolución con ID: " + datosActualizados.getIdResolucion()));
            archivo.setResolucion(resolucion);
        }

        if (datosActualizados.getNombre() != null && !datosActualizados.getNombre().isBlank()) {
            if (!archivo.getNombre().equalsIgnoreCase(datosActualizados.getNombre())
                    && archivoRepository.existsByNombre(datosActualizados.getNombre())) {
                throw new IllegalArgumentException("Ya existe un archivo con ese nombre.");
            }
            archivo.setNombre(datosActualizados.getNombre());
        }

        if (datosActualizados.getUrl() != null && !datosActualizados.getUrl().isBlank()) {
            archivo.setUrl(datosActualizados.getUrl());
        }

        if (datosActualizados.getTipo() != null && !datosActualizados.getTipo().isBlank()) {
            archivo.setTipo(datosActualizados.getTipo());
        }

        ArchivoResolucion actualizado = archivoRepository.save(archivo);
        return toResponseDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {

        ArchivoResolucion archivo = buscarEntidadPorId(id);

        archivoRepository.delete(archivo);
    }

    private void validarArchivo(
            ArchivoResolucionRequestDTO archivo) {

        if (archivo.getIdResolucion() == null) {

            throw new IllegalArgumentException(
                    "La resolución es obligatoria."
            );
        }

        if (archivo.getNombre() == null
                || archivo.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre del archivo es obligatorio."
            );
        }

        if (archivo.getUrl() == null
                || archivo.getUrl().isBlank()) {

            throw new IllegalArgumentException(
                    "La URL del archivo es obligatoria."
            );
        }

        if (archivo.getTipo() == null
                || archivo.getTipo().isBlank()) {

            throw new IllegalArgumentException(
                    "El tipo del archivo es obligatorio."
            );
        }
    }
}