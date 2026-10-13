package com.upc.demo.dto.response.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionResponseDTO {

    private Long idResolucion;
    private String numeroResolucion;
    private String titulo;
    private String descripcion;
    private Long idCategoria;
    private String nombreCategoria;
    private Long idAutor;
    private String nombreAutor;
    private LocalDateTime fechaCreacion;
    private Integer versionActual;
    private String observaciones;
    private String estado;
}
