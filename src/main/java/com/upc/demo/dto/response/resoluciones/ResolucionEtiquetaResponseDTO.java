package com.upc.demo.dto.response.resoluciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionEtiquetaResponseDTO {

    private Long idResolucionEtiqueta;
    private Long idResolucion;
    private String numeroResolucion;
    private Long idEtiqueta;
    private String nombreEtiqueta;
}
