package com.upc.demo.dto.response.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String estado;
    private String estadoSolicitud;
    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimoAcceso;
    private Long idRol;
    private String nombreRol;
}
