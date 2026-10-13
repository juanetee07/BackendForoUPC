package com.upc.demo.dto.request.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArchivoResolucionRequestDTO {

    private Long idResolucion;
    private String nombre;
    private String url;
    private String tipo;
}
