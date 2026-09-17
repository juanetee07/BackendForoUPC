package com.upc.demo.dto.request.noticias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ArchivoNoticiaRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "La URL es obligatoria")
    private String url;

    @NotBlank(message = "El tipo es obligatoria")
    private String tipo;

    @NotNull(message = "La noticia es obligatoria.")
    private Long noticiaId;
}
