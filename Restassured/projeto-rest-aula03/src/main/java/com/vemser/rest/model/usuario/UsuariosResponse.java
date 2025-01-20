package com.vemser.rest.model.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosResponse extends UsuariosRequest {

    private String message;
    @JsonProperty("_id")
    private String id;

}
