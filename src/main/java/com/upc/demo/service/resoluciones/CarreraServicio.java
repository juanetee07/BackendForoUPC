package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.CarreraRequestDTO;
import com.upc.demo.dto.response.resoluciones.CarreraResponseDTO;
import com.upc.demo.entity.resoluciones.Carrera;
import com.upc.demo.repository.resoluciones.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServicio implements ICarreraServicio {

    @Autowired
    private CarreraRepository carreraRepository;

    private CarreraResponseDTO toResponseDTO(Carrera carrera) {
        if (carrera == null) {
            return null;
        }
        return new CarreraResponseDTO(
                carrera.getIdCarrera(),
                carrera.getNombre()
        );
    }

    private Carrera buscarEntidadPorId(Long id) {
        return carreraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No existe una carrera con ID: " + id));
    }

    @Override
    public CarreraResponseDTO guardar(CarreraRequestDTO requestDTO) {

        validarCarrera(requestDTO);

        if (carreraRepository.existsByNombre(requestDTO.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe una carrera con ese nombre."
            );
        }

        Carrera carrera = Carrera.builder()
                .nombre(requestDTO.getNombre())
                .build();

        Carrera guardada = carreraRepository.save(carrera);
        return toResponseDTO(guardada);
    }

    @Override
    public CarreraResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntidadPorId(id));
    }

    @Override
    public List<CarreraResponseDTO> listarTodos() {
        return carreraRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CarreraResponseDTO actualizar(Long id, CarreraRequestDTO datosActualizados) {

        Carrera carrera = buscarEntidadPorId(id);

        validarCarrera(datosActualizados);

        if (!carrera.getNombre().equalsIgnoreCase(datosActualizados.getNombre())
                && carreraRepository.existsByNombre(datosActualizados.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe una carrera con ese nombre."
            );
        }

        carrera.setNombre(datosActualizados.getNombre());

        Carrera actualizada = carreraRepository.save(carrera);
        return toResponseDTO(actualizada);
    }

    @Override
    public void eliminar(Long id) {

        Carrera carrera = buscarEntidadPorId(id);

        carreraRepository.delete(carrera);
    }

    private void validarCarrera(CarreraRequestDTO carrera) {

        if (carrera.getNombre() == null
                || carrera.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la carrera es obligatorio."
            );
        }
    }
}