package com.upc.demo.service.resoluciones;

import com.upc.demo.entity.resoluciones.Carrera;
import com.upc.demo.repository.resoluciones.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServicio implements ICarreraServicio {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public Carrera guardar(Carrera carrera) {

        validarCarrera(carrera);

        if (carreraRepository.existsByNombre(
                carrera.getNombre())) {

            throw new IllegalArgumentException(
                    "Ya existe una carrera con ese nombre."
            );
        }

        return carreraRepository.save(carrera);
    }

    @Override
    public Carrera buscarPorId(Long id) {

        return carreraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe una carrera con ID: " + id
                        ));
    }

    @Override
    public List<Carrera> listarTodos() {

        return carreraRepository.findAll();
    }

    @Override
    public Carrera actualizar(Long id,
                              Carrera datosActualizados) {

        Carrera carrera = buscarPorId(id);

        validarCarrera(datosActualizados);

        carrera.setNombre(
                datosActualizados.getNombre()
        );

        return carreraRepository.save(carrera);
    }

    @Override
    public void eliminar(Long id) {

        Carrera carrera = buscarPorId(id);

        carreraRepository.delete(carrera);
    }

    private void validarCarrera(Carrera carrera) {

        if (carrera.getNombre() == null
                || carrera.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la carrera es obligatorio."
            );
        }
    }
}