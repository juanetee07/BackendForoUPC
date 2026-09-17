package com.upc.demo.dto.request.usuarios;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SesionRequestDTO {

    private Long idUsuario;
    private LocalDateTime fechaExpiracion;
}
