package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.CategoriaResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.CategoriaResolucionResponseDTO;
import com.upc.demo.entity.resoluciones.CategoriaResolucion;
import com.upc.demo.repository.resoluciones.CategoriaResolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaResolucionServicio
        implements ICategoriaResolucionServicio {

    @Autowired
    private CategoriaResolucionRepository categoriaRepository;

    private CategoriaResolucionResponseDTO toResponseDTO(CategoriaResolucion categoria) {
        if (categoria == null) {
            return null;
        }
        return new CategoriaResolucionResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }

    private CategoriaResolucion buscarEntidadPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No existe una categoría con ID: " + id));
    }

    @Override
    public CategoriaResolucionResponseDTO guardar(CategoriaResolucionRequestDTO requestDTO) {

        validarCategoria(requestDTO);

        if (categoriaRepository.existsByNombre(requestDTO.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe una categoría con ese nombre."
            );
        }

        CategoriaResolucion categoria = CategoriaResolucion.builder()
                .nombre(requestDTO.getNombre())
                .descripcion(requestDTO.getDescripcion())
                .build();

        CategoriaResolucion guardada = categoriaRepository.save(categoria);
        return toResponseDTO(guardada);
    }

    @Override
    public CategoriaResolucionResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public List<CategoriaResolucionResponseDTO> listarTodos() {
        return categoriaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CategoriaResolucionResponseDTO actualizar(
            Long id,
            CategoriaResolucionRequestDTO datosActualizados) {

        CategoriaResolucion categoria = buscarEntidadPorId(id);

        validarCategoria(datosActualizados);

        if (!categoria.getNombre().equalsIgnoreCase(datosActualizados.getNombre())
                && categoriaRepository.existsByNombre(datosActualizados.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe una categoría con ese nombre."
            );
        }

        categoria.setNombre(datosActualizados.getNombre());
        categoria.setDescripcion(datosActualizados.getDescripcion());

        CategoriaResolucion actualizada = categoriaRepository.save(categoria);
        return toResponseDTO(actualizada);
    }

    @Override
    public void eliminar(Long id) {

        CategoriaResolucion categoria = buscarEntidadPorId(id);

        categoriaRepository.delete(categoria);
    }

    private void validarCategoria(CategoriaResolucionRequestDTO categoria) {

        if (categoria.getNombre() == null
                || categoria.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la categoría es obligatorio."
            );
        }
    }
}