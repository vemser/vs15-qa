package com.vemser.rest.client;

import com.vemser.rest.utils.constants.UsuariosConstants;
import com.vemser.rest.model.usuario.UsuariosRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UsuariosClient extends BaseClient  {

    public Response listarUsuarios() {

        return
                given()
                        .spec(set())
                .when()
                        .get(UsuariosConstants.ENDPOINT_USUARIOS)
                ;
    }

    public Response listarUsuarios(String chave, String valor) {

        return
                given()
                        .spec(set())
                        .queryParam(chave, valor)
                .when()
                        .get(UsuariosConstants.ENDPOINT_USUARIOS)
                ;
    }

    public Response buscarUsuarioPorID(String idUsuario) {

        return
                given()
                        .spec(set())
                        .pathParam(UsuariosConstants.ID, idUsuario)
                .when()
                        .get(UsuariosConstants.ENDPOINT_USUARIOS_ID)
                ;
    }

    public Response cadastrarUsuarios(UsuariosRequest usuarioResquest) {

        return
            given()
                    .spec(super.set())
                    .body(usuarioResquest)
            .when()
                    .post(UsuariosConstants.ENDPOINT_USUARIOS)
            ;

    }

    public Response atualizarUsuario(UsuariosRequest usuarioResquest, String idUsuario) {

        return
                given()
                        .spec(super.set())
                        .pathParam(UsuariosConstants.ID, idUsuario)
                        .body(usuarioResquest)
                .when()
                        .put(UsuariosConstants.ENDPOINT_USUARIOS_ID)
                ;
    }

    public Response excluirUsuarios(String idUsuario) {

        return
                given()
                        .spec(super.set())
                        .pathParam(UsuariosConstants.ID, idUsuario)
                .when()
                        .delete(UsuariosConstants.ENDPOINT_USUARIOS_ID)
                ;
    }
}
