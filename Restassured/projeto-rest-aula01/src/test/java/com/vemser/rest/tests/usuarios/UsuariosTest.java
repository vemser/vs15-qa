package com.vemser.rest.tests.usuarios;

import com.vemser.rest.model.UsuarioModel;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class UsuariosTest {

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testListarTodosUsuariosComSucesso() {

        given()
                .log().all()
        .when()
                .get("/usuarios")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void testListarUsuariosPorNomeComSucesso() {

        String nome = "Luiz Henrique de Castro Ramos";

        given()
                .log().all()
                .queryParam("nome", nome)
        .when()
                .get("/usuarios")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucesso() {

        String idUsuario = "clkiIWmheYiiQ0jW";

        given()
                .log().all()
                .pathParam("_id", idUsuario)
        .when()
                .get("/usuarios/{_id}")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void testDeveCadastrarUsuarioComSucesso() {

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome("Alyson Campos QA 123");
        usuario.setEmail("qa.email@email.com");
        usuario.setPassword("alyson123");
        usuario.setAdministrador("true");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
        .when()
                .post("/usuarios")
        .then()
                .log().all()
                .statusCode(201)
        ;
    }

    @Test
    public void testDeveAtualizarUsuarioComSucesso() {

        String idUsuario = "clkiIWmheYiiQ0jW";

        UsuarioModel usuarioAtualizado = new UsuarioModel();
        usuarioAtualizado.setNome("Alyson Campos QA");
        usuarioAtualizado.setEmail("qa.email@email.com");
        usuarioAtualizado.setPassword("alyson123");
        usuarioAtualizado.setAdministrador("true");

        given()
                .log().all()
                .pathParam("_id", idUsuario)
                .contentType(ContentType.JSON)
                .body(usuarioAtualizado)
        .when()
                .put("/usuarios/{_id}")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void testDeveExcluirUsuarioComSucesso() {

        String idUsuario = "clkiIWmheYiiQ0jW";

        given()
                .log().all()
                .pathParam("id", idUsuario)
        .when()
                .delete("/usuarios/{id}")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }
}
