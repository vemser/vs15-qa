package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.utils.constants.UsuariosConstants;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.usuario.UsuariosResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ListarUsuariosTest {

    private static UsuariosClient usuarioClient = new UsuariosClient();

    @Test
    public void testDeveListarTodosUsuariosComSucesso() {

        usuarioClient.listarUsuarios()
                .then()
                    .assertThat()
                        .statusCode(200)
                        .time(lessThan(5000L))
                ;
    }

    @Test
    public void testDeveListarUsuariosPorNomeComSucesso() {

        String nomeExistente = UsuariosDataFactory.retornarUsuarioExistente().getNome();

        usuarioClient.listarUsuarios(UsuariosConstants.NOME, nomeExistente)
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("usuarios[0].nome", equalTo(nomeExistente))
                ;
    }

    @Test
    public void testSchemaDeveBuscarUsuarioPorIDComSucesso() {

        UsuariosResponse usuarioExistente = UsuariosDataFactory.retornarUsuarioExistente();

        usuarioClient.buscarUsuarioPorID(usuarioExistente.getId())
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schemas/usuarios_por_id.json"))
                ;
    }

    @Test
    public void testDeveBuscarUsuarioPorIDComSucesso() {

        UsuariosResponse usuarioExistente = UsuariosDataFactory.retornarUsuarioExistente();

        UsuariosResponse response = usuarioClient.buscarUsuarioPorID(usuarioExistente.getId())
                .then()
                    .assertThat()
                        .statusCode(200)
                    .extract()
                        .as(UsuariosResponse.class)
                ;

        Assertions.assertAll("Usuario Por ID",
                () -> Assertions.assertEquals(usuarioExistente.getNome(), response.getNome()),
                () -> Assertions.assertEquals(usuarioExistente.getEmail(), response.getEmail()),
                () -> Assertions.assertEquals(usuarioExistente.getId(), response.getId())
        );
    }
}
