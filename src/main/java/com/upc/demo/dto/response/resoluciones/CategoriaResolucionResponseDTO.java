package com.upc.demo.dto.response.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResolucionResponseDTO {

    private Long idCategoria;
    private String nombre;
    private String descripcion;
}
