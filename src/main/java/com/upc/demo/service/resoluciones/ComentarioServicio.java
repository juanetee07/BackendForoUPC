package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.ComentarioRequestDTO;
import com.upc.demo.dto.response.resoluciones.ComentarioResponseDTO;
import com.upc.demo.entity.resoluciones.Carrera;
import com.upc.demo.entity.resoluciones.Comentario;
import com.upc.demo.entity.resoluciones.Resolucion;
import com.upc.demo.entity.usuarios.Usuario;
import com.upc.demo.repository.resoluciones.CarreraRepository;
import com.upc.demo.repository.resoluciones.ComentarioRepository;
import com.upc.demo.repository.resoluciones.ResolucionRepository;
import com.upc.demo.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComentarioServicio implements IComentarioServicio {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    private ComentarioResponseDTO toResponseDTO(Comentario comentario) {
        if (comentario == null) {
            return null;
        }
        ComentarioResponseDTO dto = new ComentarioResponseDTO();
        dto.setIdComentario(comentario.getIdComentario());

        if (comentario.getUsuario() != null) {
            dto.setIdUsuario(comentario.getUsuario().getIdUsuario());
            String nombreUsuario = comentario.getUsuario().getNombre();
            if (comentario.getUsuario().getApellido() != null && !comentario.getUsuario().getApellido().isBlank()) {
                nombreUsuario += " " + comentario.getUsuario().getApellido();
            }
            dto.setNombreUsuario(nombreUsuario);
        }

        if (comentario.getResolucion() != null) {
            dto.setIdResolucion(comentario.getResolucion().getIdResolucion());
            dto.setNumeroResolucion(comentario.getResolucion().getNumeroResolucion());
        }

        if (comentario.getCarrera() != null) {
            dto.setIdCarrera(comentario.getCarrera().getIdCarrera());
            dto.setNombreCarrera(comentario.getCarrera().getNombre());
        }

        dto.setAnioCarrera(comentario.getAnioCarrera());
        dto.setContenido(comentario.getContenido());
        dto.setFechaCreacion(comentario.getFechaCreacion());
        dto.setEstado(comentario.getEstado());

        return dto;
    }

    private Comentario buscarEntidadPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No existe un comentario con ID: " + id));
    }

    @Override
    public ComentarioResponseDTO guardar(ComentarioRequestDTO requestDTO) {

        validarComentario(requestDTO);

        Usuario usuario = usuarioRepository.findById(requestDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("No existe un usuario con ID: " + requestDTO.getIdUsuario()));

        Resolucion resolucion = resolucionRepository.findById(requestDTO.getIdResolucion())
                .orElseThrow(() -> new RuntimeException("No existe una resolución con ID: " + requestDTO.getIdResolucion()));

        Carrera carrera = carreraRepository.findById(requestDTO.getIdCarrera())
                .orElseThrow(() -> new RuntimeException("No existe una carrera con ID: " + requestDTO.getIdCarrera()));

        Comentario comentario = Comentario.builder()
                .usuario(usuario)
                .resolucion(resolucion)
                .carrera(carrera)
                .anioCarrera(requestDTO.getAnioCarrera())
                .contenido(requestDTO.getContenido())
                .fechaCreacion(LocalDateTime.now())
                .estado(requestDTO.getEstado() != null && !requestDTO.getEstado().isBlank() ? requestDTO.getEstado() : "ACTIVO")
                .build();

        Comentario guardado = comentarioRepository.save(comentario);
        return toResponseDTO(guardado);
    }

    @Override
    public ComentarioResponseDTO buscarPorId(Long id) {

        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public List<ComentarioResponseDTO> listarTodos() {

        return comentarioRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ComentarioResponseDTO actualizar(Long id, ComentarioRequestDTO datosActualizados) {

        Comentario comentario = buscarEntidadPorId(id);

        if (datosActualizados.getIdCarrera() != null) {
            Carrera carrera = carreraRepository.findById(datosActualizados.getIdCarrera())
                    .orElseThrow(() -> new RuntimeException("No existe una carrera con ID: " + datosActualizados.getIdCarrera()));
            comentario.setCarrera(carrera);
        }

        if (datosActualizados.getAnioCarrera() != null) {
            comentario.setAnioCarrera(datosActualizados.getAnioCarrera());
        }

        if (datosActualizados.getContenido() != null && !datosActualizados.getContenido().isBlank()) {
            comentario.setContenido(datosActualizados.getContenido());
        }

        if (datosActualizados.getEstado() != null && !datosActualizados.getEstado().isBlank()) {
            comentario.setEstado(datosActualizados.getEstado());
        }

        Comentario actualizado = comentarioRepository.save(comentario);
        return toResponseDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {

        Comentario comentario = buscarEntidadPorId(id);

        comentarioRepository.delete(comentario);
    }

    private void validarComentario(ComentarioRequestDTO comentario) {

        if (comentario.getIdUsuario() == null) {
            throw new IllegalArgumentException(
                    "El usuario es obligatorio."
            );
        }

        if (comentario.getIdResolucion() == null) {
            throw new IllegalArgumentException(
                    "La resolución es obligatoria."
            );
        }

        if (comentario.getIdCarrera() == null) {
            throw new IllegalArgumentException(
                    "La carrera es obligatoria."
            );
        }

        if (comentario.getAnioCarrera() == null) {
            throw new IllegalArgumentException(
                    "El año de carrera es obligatorio."
            );
        }

        if (comentario.getContenido() == null
                || comentario.getContenido().isBlank()) {
            throw new IllegalArgumentException(
                    "El contenido del comentario es obligatorio."
            );
        }
    }
}