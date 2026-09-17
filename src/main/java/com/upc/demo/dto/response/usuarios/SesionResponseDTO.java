package com.upc.demo.dto.response.usuarios;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SesionResponseDTO {

    private Long idSesion;
    private String tokenJwt;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaExpiracion;
    private Boolean activa;
    private Long idUsuario;
    private String emailUsuario;
}
