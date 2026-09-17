package com.upc.demo.dto.request.noticias;

import com.upc.demo.entity.noticias.EstadoNoticia;
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

public class NoticiaRequest {

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    @NotBlank(message = "El cuerpo de la noticia es obligatorio.")
    private String cuerpo;

    @NotNull(message = "La categoría es obligatoria.")
    private Long categoriaId;

    private EstadoNoticia estado;
}
