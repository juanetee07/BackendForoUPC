package com.upc.demo.dto.request.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroUsuarioRequestDTO {

    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String contraseña;

}
