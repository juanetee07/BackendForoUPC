package com.upc.demo.dto.request.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionRequestDTO {

    private String numeroResolucion;
    private String titulo;
    private String descripcion;
    private Long idCategoria;
    private Long idAutor;
    private Integer versionActual;
    private String observaciones;
    private String estado;
}
