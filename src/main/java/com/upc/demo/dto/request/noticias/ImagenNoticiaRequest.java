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

public class ImagenNoticiaRequest {

    @NotBlank(message = "La URL es obligatoria")
    private String url;

    @NotBlank(message = "El epígrafe es obligatorio")
    private String epigrafe;
    
    private Long noticiaId;

}
