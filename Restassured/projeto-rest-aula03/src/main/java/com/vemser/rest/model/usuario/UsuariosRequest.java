package com.vemser.rest.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosRequest {

    private String nome;
    private String email;
    private String password;
    private String administrador;

}
