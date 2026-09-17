package com.upc.demo.dto.response.noticias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ImagenNoticiaResponse {

    private Long id;
    private String url;
    private String epigrafe;

}
