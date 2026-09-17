package com.upc.demo.dto.response.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArchivoResolucionResponseDTO {

    private Long idArchivo;
    private Long idResolucion;
    private String numeroResolucion;
    private String nombre;
    private String url;
    private String tipo;
}
