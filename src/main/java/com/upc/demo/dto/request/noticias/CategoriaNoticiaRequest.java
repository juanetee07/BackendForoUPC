package com.upc.demo.dto.request.noticias;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaNoticiaRequest {

    @NotBlank(message = "El nombre de la categoría es obligatorio.")
    private String nombre;
}