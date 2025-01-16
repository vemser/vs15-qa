package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuarioClient;
import com.vemser.rest.data.factory.UsuarioDataFactory;
import com.vemser.rest.model.Usuario;
import com.vemser.rest.model.UsuarioRequest;
import com.vemser.rest.model.UsuarioResponse;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsuariosTest {

    private UsuarioClient usuarioClient = new UsuarioClient();
    Faker faker = new Faker(new Locale("PT-BR"));
    Random geradorBolean = new Random();

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
    public void testSchemaBuscarUsuarioPorID() {

        String idUsuario = "0MwHOKkT6gbagbPO";

        given()
                .log().all()
                .pathParam("_id", idUsuario)
        .when()
                .get("/usuarios/{_id}")
        .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/usuarios_por_id.json"))
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucessoHamcrest() {

        String idUsuario = "0MwHOKkT6gbagbPO";

        given()
                .log().all()
                .pathParam("_id", idUsuario)
        .when()
                .get("/usuarios/{_id}")
        .then()
                .log().all()
                .header("Content-Type", "application/json; charset=utf-8")
                .time(lessThan(5000L))
                .statusCode(200)
                .body("_id", equalTo(idUsuario))
                .body("email", containsStringIgnoringCase("Santiago"))
        ;
    }

    @Test
    public void testBuscarUsuarioPorIDComSucessoAssertions() {

        String idUsuario = "BRuqEKrA4IpiBmd6";

        UsuarioResponse response =
        given()
                .log().all()
                .pathParam("_id", idUsuario)
        .when()
                .get("/usuarios/{_id}")
        .then()
                .log().all()
                .statusCode(200)
                .extract()
                    .as(UsuarioResponse.class)
        ;

        Assertions.assertEquals("Igor Brites", response.getNome());
        Assertions.assertEquals("ricardo.daconceicao@gmail.com", response.getEmail());
        Assertions.assertEquals("33s44caw0776840", response.getPassword());
        Assertions.assertEquals("false", response.getAdministrador());
        Assertions.assertNotNull(response.getId());
    }

    @Test
    public void testBuscarUsuarioPorIDComSucessoSoftAssertions() {

        String idUsuario = "0MwHOKkT6gbagbPO";

        UsuarioResponse response =
                given()
                        .pathParam("_id", idUsuario)
                .when()
                        .get("/usuarios/{_id}")
                .then()
                        .statusCode(200)
                        .extract()
                        .as(UsuarioResponse.class)
                ;

        Assertions.assertAll("response",
                () -> Assertions.assertEquals("Luizzz Henrique de Castro Ramos", response.getNome()),
                () -> Assertions.assertEquals("Santiago000.Bashirian@hotmail.com", response.getEmail()),
                () -> Assertions.assertEquals("auIxKV7i4rTV6UBBBB", response.getPassword())
        );
    }

    @Test
    public void testDeveCadastrarUsuarioComDadosValidos() {

        Usuario usuario = UsuarioDataFactory.usuarioValido();

        usuarioClient.cadastrarUsuarios(usuario)
        .then()
                .statusCode(200)
                .body("message", equalTo("Cadastro realizado com sucessooooo"))
                .body("_id", notNullValue())
        ;
    }

    @Test
    public void testTentarCadastrarUsuarioComEmailEmBranco() {

        Usuario usuarioSemEmail = UsuarioDataFactory.usuarioSemEmail();

        usuarioClient.cadastrarUsuarios(usuarioSemEmail)
        .then()
                .log().all()
                .statusCode(400)
                .body("email", equalTo("email não pode ficar em branco"))
        ;
    }

    @Test
    public void testDeveAtualizarUsuarioComSucesso() {

        String idUsuario = "clkiIWmheYiiQ0jW";

        UsuarioRequest usuarioAtualizado = new UsuarioRequest();
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
