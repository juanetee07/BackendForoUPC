package com.upc.demo.dto.response.noticias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArchivoNoticiaResponse {

    private Long id;
    private String nombre;
    private String url;
    private String tipo;

}
