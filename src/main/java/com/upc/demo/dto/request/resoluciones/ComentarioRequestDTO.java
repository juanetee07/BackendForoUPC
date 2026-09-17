package com.upc.demo.dto.request.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioRequestDTO {

    private Long idUsuario;
    private Long idResolucion;
    private Long idCarrera;
    private Integer anioCarrera;
    private String contenido;
    private String estado;
}
