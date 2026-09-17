package com.upc.demo.service.resoluciones;

import com.upc.demo.dto.request.resoluciones.CarreraRequestDTO;
import com.upc.demo.dto.response.resoluciones.CarreraResponseDTO;

import java.util.List;

public interface ICarreraServicio {

    CarreraResponseDTO guardar(CarreraRequestDTO requestDTO);

    CarreraResponseDTO buscarPorId(Long id);

    List<CarreraResponseDTO> listarTodos();

    CarreraResponseDTO actualizar(Long id, CarreraRequestDTO requestDTO);

    void eliminar(Long id);
}