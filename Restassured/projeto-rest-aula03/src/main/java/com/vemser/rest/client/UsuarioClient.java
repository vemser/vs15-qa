package com.vemser.rest.client;

import com.vemser.rest.model.Usuario;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UsuarioClient extends BaseClient  {

    private final String USUARIOS = "/usuarios";

    public Response cadastrarUsuarios(Usuario usuario) {

        return
            given()
                    .spec(super.set())
                    .body(usuario)
            .when()
                    .post(USUARIOS)
            ;

    }
}
