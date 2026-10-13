package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ResolucionRequestDTO;
import com.upc.demo.dto.response.resoluciones.ResolucionResponseDTO;
import com.upc.demo.entity.resoluciones.CategoriaResolucion;
import com.upc.demo.entity.resoluciones.Resolucion;
import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.resoluciones.CategoriaResolucionRepository;
import com.upc.demo.repository.resoluciones.ResolucionRepository;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResolucionServicio implements IResolucionServicio {

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private CategoriaResolucionRepository categoriaResolucionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ResolucionResponseDTO toResponseDTO(Resolucion resolucion) {
        if (resolucion == null) {
            return null;
        }
        ResolucionResponseDTO dto = new ResolucionResponseDTO();
        dto.setIdResolucion(resolucion.getIdResolucion());
        dto.setNumeroResolucion(resolucion.getNumeroResolucion());
        dto.setTitulo(resolucion.getTitulo());
        dto.setDescripcion(resolucion.getDescripcion());

        if (resolucion.getCategoria() != null) {
            dto.setIdCategoria(resolucion.getCategoria().getIdCategoria());
            dto.setNombreCategoria(resolucion.getCategoria().getNombre());
        }

        if (resolucion.getAutor() != null) {
            dto.setIdAutor(resolucion.getAutor().getIdUsuario());
            String nombreCompleto = resolucion.getAutor().getNombre();
            if (resolucion.getAutor().getApellido() != null && !resolucion.getAutor().getApellido().isBlank()) {
                nombreCompleto += " " + resolucion.getAutor().getApellido();
            }
            dto.setNombreAutor(nombreCompleto);
        }

        dto.setFechaCreacion(resolucion.getFechaCreacion());
        dto.setVersionActual(resolucion.getVersionActual());
        dto.setObservaciones(resolucion.getObservaciones());
        dto.setEstado(resolucion.getEstado());

        return dto;
    }

    private Resolucion buscarEntidadPorId(Long id) {
        return resolucionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No existe una resolución con ID: " + id));
    }

    @Override
    public ResolucionResponseDTO guardar(ResolucionRequestDTO requestDTO) {

        validarNuevaResolucion(requestDTO);

        if (resolucionRepository.existsByNumeroResolucion(
                requestDTO.getNumeroResolucion())) {

            throw new IllegalArgumentException(
                    "Ya existe una resolución con el número: "
                            + requestDTO.getNumeroResolucion()
            );
        }

        CategoriaResolucion categoria = categoriaResolucionRepository.findById(requestDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + requestDTO.getIdCategoria()));

        Usuario autor = usuarioRepository.findById(requestDTO.getIdAutor())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + requestDTO.getIdAutor()));

        Resolucion resolucion = Resolucion.builder()
                .numeroResolucion(requestDTO.getNumeroResolucion())
                .titulo(requestDTO.getTitulo())
                .descripcion(requestDTO.getDescripcion())
                .categoria(categoria)
                .autor(autor)
                .fechaCreacion(LocalDateTime.now())
                .versionActual(requestDTO.getVersionActual() != null ? requestDTO.getVersionActual() : 1)
                .observaciones(requestDTO.getObservaciones())
                .estado(requestDTO.getEstado() != null && !requestDTO.getEstado().isBlank() ? requestDTO.getEstado() : "ACTIVA")
                .build();

        Resolucion resolucionGuardada = resolucionRepository.save(resolucion);
        return toResponseDTO(resolucionGuardada);
    }

    @Override
    public ResolucionResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public List<ResolucionResponseDTO> listarTodos() {
        return resolucionRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ResolucionResponseDTO actualizar(Long id, ResolucionRequestDTO datosActualizados) {

        Resolucion resolucion = buscarEntidadPorId(id);

        validarActualizacion(datosActualizados);

        if (datosActualizados.getIdCategoria() != null) {
            CategoriaResolucion categoria = categoriaResolucionRepository.findById(datosActualizados.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + datosActualizados.getIdCategoria()));
            resolucion.setCategoria(categoria);
        }

        if (datosActualizados.getIdAutor() != null) {
            Usuario autor = usuarioRepository.findById(datosActualizados.getIdAutor())
                    .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + datosActualizados.getIdAutor()));
            resolucion.setAutor(autor);
        }

        if (datosActualizados.getNumeroResolucion() != null && !datosActualizados.getNumeroResolucion().isBlank()
                && !datosActualizados.getNumeroResolucion().equals(resolucion.getNumeroResolucion())) {
            if (resolucionRepository.existsByNumeroResolucion(datosActualizados.getNumeroResolucion())) {
                throw new IllegalArgumentException(
                        "Ya existe una resolución con el número: " + datosActualizados.getNumeroResolucion()
                );
            }
            resolucion.setNumeroResolucion(datosActualizados.getNumeroResolucion());
        }

        resolucion.setTitulo(datosActualizados.getTitulo());
        resolucion.setDescripcion(datosActualizados.getDescripcion());
        resolucion.setObservaciones(datosActualizados.getObservaciones());
        if (datosActualizados.getEstado() != null && !datosActualizados.getEstado().isBlank()) {
            resolucion.setEstado(datosActualizados.getEstado());
        }
        if (datosActualizados.getVersionActual() != null) {
            resolucion.setVersionActual(datosActualizados.getVersionActual());
        }

        Resolucion resolucionActualizada = resolucionRepository.save(resolucion);
        return toResponseDTO(resolucionActualizada);
    }

    @Override
    public void eliminar(Long id) {

        Resolucion resolucion = buscarEntidadPorId(id);

        // Eliminación lógica según regla de negocio
        resolucion.setEstado("ARCHIVADA");

        resolucionRepository.save(resolucion);
    }

    /**
     * Validaciones para crear una nueva resolución.
     */
    private void validarNuevaResolucion(ResolucionRequestDTO resolucion) {

        if (resolucion.getNumeroResolucion() == null
                || resolucion.getNumeroResolucion().isBlank()) {

            throw new IllegalArgumentException(
                    "El número de resolución es obligatorio."
            );
        }

        if (resolucion.getTitulo() == null
                || resolucion.getTitulo().isBlank()) {

            throw new IllegalArgumentException(
                    "El título es obligatorio."
            );
        }

        if (resolucion.getIdCategoria() == null) {

            throw new IllegalArgumentException(
                    "La categoría es obligatoria."
            );
        }

        if (resolucion.getIdAutor() == null) {

            throw new IllegalArgumentException(
                    "El autor es obligatorio."
            );
        }
    }

    /**
     * Validaciones para actualizar una resolución.
     */
    private void validarActualizacion(ResolucionRequestDTO resolucion) {

        if (resolucion.getTitulo() == null
                || resolucion.getTitulo().isBlank()) {

            throw new IllegalArgumentException(
                    "El título es obligatorio."
            );
        }
    }
}